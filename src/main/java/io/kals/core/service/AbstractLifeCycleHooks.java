package io.kals.core.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * Interface defining lifecycle hooks for CRUD operations.
 * These methods can be overridden to inject custom logic before or after standard database operations.
 *
 * @param <E> the entity type
 * @param <D> the DTO type
 * @param <I> the identifier type
 */
@Component
public interface AbstractLifeCycleHooks<E, D, I> {

    /**
     * Invoked before a new entity is created.
     * @param dto the data transfer object containing new entity data
     */
    void beforeCreate(D dto);

    /**
     * Invoked after a new entity has been successfully created.
     * @param entity the newly created entity
     */
    void afterCreate(E entity);

    /**
     * Invoked before an existing entity is updated.
     * @param id the identifier of the entity to update
     * @param dto the data transfer object containing updated data
     */
    void beforeUpdate(I id, D dto);

    /**
     * Invoked after an entity has been successfully updated.
     * @param entity the updated entity
     */
    void afterUpdate(E entity);

    /**
     * Invoked before an entity is deleted.
     * @param entity the entity marked for deletion
     */
    void beforeDelete(E entity);

    /**
     * Invoked after an entity has been successfully deleted.
     * @param id the identifier of the deleted entity
     */
    void afterDelete(I id);

    /**
     * Invoked before fetching an entity by its identifier.
     * @param id the identifier of the entity to fetch
     */
    void beforeGet(I id);

    /**
     * Invoked after an entity is fetched by its identifier.
     * @param entity the fetched entity
     */
    void afterGet(E entity);

    /**
     * Invoked before fetching a paginated list of entities.
     * @param pageable pagination and sorting parameters
     * @param query the search query string
     */
    void beforeGetAll(Pageable pageable, String query);

    /**
     * Invoked after fetching a paginated list of entities.
     * @param page the fetched page of entities
     */
    void afterGetAll(Page<E> page);

    /**
     * Invoked to perform validation before the creation process begins.
     * @param d the data transfer object to validate
     */
    void validationBeforeCreate(D d);

    /**
     * Invoked to perform validation on the entity after it has been created but before returning.
     * @param e the newly created entity
     */
    void validationAfterCreate(E e);

    /**
     * General validation method for DTOs.
     * @param d the data transfer object to validate
     */
    void validation(D d);
}
