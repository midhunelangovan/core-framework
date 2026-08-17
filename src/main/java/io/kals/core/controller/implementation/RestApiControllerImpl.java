package io.kals.core.controller.implementation;

import io.kals.security.aspect.IsAdmin;
import io.kals.security.aspect.IsEditor;
import io.kals.security.aspect.IsReader;
import jakarta.validation.Valid;
import io.kals.core.controller.RestApiBaseController;
import io.kals.core.model.PageResponse;
import io.kals.core.service.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Generic REST API abstract implementation.
 * Provides standard endpoints by delegating to an underlying CRUD service.
 * <p>
 * E -> Entity
 * D -> DTO/Domain Model
 * I -> Identifier type (Long, UUID, String, etc.)
 */
public abstract class RestApiControllerImpl<E, D, I> implements RestApiBaseController<E, D, I> {

    private final String resourceName;

    @Autowired
    AbstractCrudService<E, D, I> service;

    /**
     * Constructs a new AbstractRestApiController.
     *
     * @param service the CRUD service to handle business logic
     */
    protected RestApiControllerImpl(String resourceName, AbstractCrudService<E, D, I> service) {
        this.resourceName = resourceName;
        this.service = service;
    }

    @IsReader(resourceName = "#{resourceName}")
    public PageResponse<D> getAll(Pageable pageable, @RequestParam(value = "q", required = false) String q) {
        return service.getAll(pageable, q);
    }

    @IsReader(resourceName = "#{resourceName}")
    public ResponseEntity<D> getById(@PathVariable("id") I id) {
        D dto = service.getById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @IsAdmin(resourceName = "#{resourceName}")
    public ResponseEntity<D> create(@Valid @RequestBody D dto) {
        D res = service.create(dto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @IsAdmin(resourceName = "#{resourceName}")
    public ResponseEntity<List<D>> createAll(@RequestBody List<D> dtos) {
        List<D> dList = service.createAll(dtos);
        return new ResponseEntity<>(dList, HttpStatus.CREATED);
    }

    @IsEditor(resourceName = "#{resourceName}")
    public ResponseEntity<D> update(@RequestBody D dto, @PathVariable("id") I id) {
        D res = service.update(dto, id);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @IsEditor(resourceName = "#{resourceName}")
    public ResponseEntity<Void> delete(@PathVariable("id") I id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
