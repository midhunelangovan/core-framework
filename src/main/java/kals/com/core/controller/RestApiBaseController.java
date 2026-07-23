package kals.com.core.controller;

import kals.com.core.model.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface RestApiBaseController<E, D, I> {

    /**
     * Returns paginated resources.
     * <p>
     * Supports optional filtering through CommonSpecification.
     * <p>
     * Example:
     * GET /users?page=0&size=10&q=name==John
     */
    @GetMapping("")
    PageResponse<D> getAll(Pageable pageable, String q);

    /**
     * Returns a single resource by ID.
     */
    @GetMapping("/{id}")
    ResponseEntity<D> getById(I i);

    /**
     * Creates a single resource.
     */
    @PostMapping("")
    ResponseEntity<D> create(D d);


    /**
     * Bulk create endpoint.
     */
    @PostMapping("/create-all")
    ResponseEntity<List<D>> createAll(List<D> d);


    /**
     * Updates an existing resource.
     */
    @PutMapping("/{id}")
    ResponseEntity<D> update(D d, I i);


    /**
     * Deletes a resource by ID.
     */
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(I i);


}
