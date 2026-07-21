package kals.com.core.service.implementation;

import kals.com.core.exception.ResourceNotFoundException;
import kals.com.core.mapper.BaseMapper;
import kals.com.core.model.PageResponse;
import kals.com.core.service.RestApiService;
import kals.com.core.specification.CommonSpecification;
import kals.com.core.utility.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Generic REST API implementation.
 * <p>
 * E -> Entity
 * D -> DTO/Domain Model
 * I -> Identifier type (Long, UUID, String, etc.)
 * R -> Repository (supports CRUD + Specifications)
 * M -> Mapper (Entity <-> DTO)
 */
public class RestApiServiceImpl
        <
                E,
                D,
                I,
                R extends JpaRepository<E, I> & JpaSpecificationExecutor<E>,
                M extends BaseMapper<E, D>
                >
        implements RestApiService<E, D, I, R, M> {

    /**
     * Generic repository injected by Spring.
     */
    @Autowired
    R repository;

    /**
     * Generic mapper injected by Spring.
     */
    @Autowired
    M mapper;

    /**
     * Returns paginated resources.
     * <p>
     * Supports optional filtering through CommonSpecification.
     * <p>
     * Example:
     * GET /users?page=0&size=10&q=name==John
     */
    @Override
    @GetMapping("")
    public ResponseEntity<PageResponse<D>> getAll(
            Pageable pageable,
            @RequestParam(value = "q", required = false) String q) {

        // Build dynamic specification using query string
        CommonSpecification<E> specification = new CommonSpecification<>(q);

        // Fetch filtered + paginated entities
        Page<E> e = repository.findAll(specification, pageable);

        // Convert Spring Page into custom page model
        kals.com.core.model.Page page =
                PageUtil.convertRawPageToPageDomain(e);

        // Convert entities into DTOs
        List<D> d = mapper.toDtoList(e.getContent());

        // Build generic response
        PageResponse<D> pageResponse =
                PageResponse.<D>builder()
                        .content(d)
                        .page(page)
                        .build();

        return new ResponseEntity<>(pageResponse, HttpStatus.OK);
    }

    /**
     * Returns a single resource by ID.
     */
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<D> getById(@PathVariable("id") I i) {

        Optional<E> e = repository.findById(i);

        if (e.isEmpty()) {
            throw new ResourceNotFoundException("Request Resource Not Found");
        }

        D res = mapper.toDto(e.get());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * Creates a single resource.
     */
    @Override
    @PostMapping("")
    public ResponseEntity<D> create(@RequestBody D d) {

        // Convert DTO -> Entity
        E e = mapper.toEntity(d);

        // Save entity
        E saved = repository.save(e);

        // Convert Entity -> DTO
        D res = mapper.toDto(saved);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    /**
     * Bulk create endpoint.
     */
    @Override
    @PostMapping("/create-all")
    public ResponseEntity<List<D>> createAll(@RequestBody List<D> d) {

        // Convert DTOs -> Entities
        List<E> eList = mapper.toEntityList(d);

        // Save all entities
        List<E> saved = repository.saveAll(eList);

        // Convert back to DTOs
        List<D> dList = mapper.toDtoList(saved);

        return new ResponseEntity<>(dList, HttpStatus.CREATED);
    }

    /**
     * Updates an existing resource.
     */
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<D> update(
            @RequestBody D d,
            @PathVariable("id") I i) {

        // Ensure the resource exists
        Optional<E> existing = repository.findById(i);

        if (existing.isEmpty()) {
            throw new ResourceNotFoundException("Request Resource Not Found");
        }

        // Convert DTO -> Entity
        E e = mapper.toEntity(d);

        // Save updated entity
        D res = mapper.toDto(repository.save(e));

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    /**
     * Deletes a resource by ID.
     */
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") I i) {

        // Ensure the resource exists
        Optional<E> e = repository.findById(i);

        if (e.isEmpty()) {
            throw new ResourceNotFoundException("Request Resource Not Found");
        }

        // Delete resource
        repository.deleteById(i);

        // HTTP 204 No Content
        return ResponseEntity.noContent().build();
    }
}