package kals.com.core.service;

import kals.com.core.model.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Base contract interface defining standard CRUD (Create, Read, Update, Delete) operations.
 *
 * @param <D> the Data Transfer Object (DTO) or domain model type
 * @param <I> the entity identifier type
 */
public interface AbstractCrudBaseService<D, I> {

    /**
     * Retrieves a paginated list of records, optionally filtered by a search query string.
     *
     * @param pageable pagination and sorting parameters
     * @param q        search or query filter string
     * @return a {@link PageResponse} containing the page content of DTOs and pagination metadata
     */
    PageResponse<D> getAll(Pageable pageable, String q);

    /**
     * Retrieves a single record by its unique identifier.
     *
     * @param id the unique identifier of the entity to retrieve
     * @return the retrieved DTO instance
     */
    D getById(I id);

    /**
     * Creates a new record.
     *
     * @param dto the DTO containing data for creation
     * @return the created DTO instance
     */
    D create(D dto);

    /**
     * Creates multiple records in a batch.
     *
     * @param dtos the list of DTOs to create
     * @return the list of created DTO instances
     */
    List<D> createAll(List<D> dtos);

    /**
     * Updates an existing record identified by its unique ID.
     *
     * @param dto the DTO containing updated data
     * @param id  the unique identifier of the entity to update
     * @return the updated DTO instance
     */
    D update(D dto, I id);

    /**
     * Deletes a record by its unique identifier.
     *
     * @param id the unique identifier of the entity to delete
     */
    void delete(I id);

}

