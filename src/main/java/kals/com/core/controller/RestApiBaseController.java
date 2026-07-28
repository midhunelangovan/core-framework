package kals.com.core.controller;

import kals.com.core.model.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Base REST API controller interface defining standard endpoints for CRUD operations.
 * 
 * @param <E> the entity type
 * @param <D> the DTO or domain model type
 * @param <I> the identifier type
 */
public interface RestApiBaseController<E, D, I> {

    /**
     * Retrieves a paginated list of resources, optionally filtered by a query string.
     * <p>
     * Example usage: GET /api/resource?page=0&size=10&q=name==John
     *
     * @param pageable pagination and sorting parameters
     * @param q optional search query string for filtering
     * @return a paginated response containing the list of DTOs
     */
    @GetMapping("")
    PageResponse<D> getAll(Pageable pageable, String q);

    /**
     * Retrieves a single resource by its unique identifier.
     *
     * @param id the unique identifier of the resource
     * @return the resource DTO wrapped in a ResponseEntity
     */
    @GetMapping("/{id}")
    ResponseEntity<D> getById(@PathVariable("id") I id);

    /**
     * Creates a new resource.
     *
     * @param dto the DTO containing the data for the new resource
     * @return the created resource DTO wrapped in a ResponseEntity
     */
    @PostMapping("")
    ResponseEntity<D> create(@RequestBody D dto);

    /**
     * Creates multiple resources in a bulk operation.
     *
     * @param dtos the list of DTOs to create
     * @return the list of created resource DTOs wrapped in a ResponseEntity
     */
    @PostMapping("/create-all")
    ResponseEntity<List<D>> createAll(@RequestBody List<D> dtos);

    /**
     * Updates an existing resource identified by its unique ID.
     *
     * @param dto the DTO containing updated data
     * @param id the unique identifier of the resource to update
     * @return the updated resource DTO wrapped in a ResponseEntity
     */
    @PutMapping("/{id}")
    ResponseEntity<D> update(@RequestBody D dto, @PathVariable("id") I id);

    /**
     * Deletes a resource by its unique identifier.
     *
     * @param id the unique identifier of the resource to delete
     * @return a ResponseEntity indicating success (typically 204 No Content)
     */
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") I id);

}
