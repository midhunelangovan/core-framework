package kals.com.core.service;

import kals.com.core.exception.Exceptions.ResourceNotFoundException;
import kals.com.core.mapper.BaseMapper;
import kals.com.core.model.PageResponse;
import kals.com.core.repository.AbstractBaseRepository;
import kals.com.core.specification.CommonSpecification;
import kals.com.core.utility.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Base abstract service implementation for CRUD operations.
 * Implements both core CRUD functionalities and lifecycle hooks.
 * 
 * @param <E> the entity type
 * @param <D> the DTO type
 * @param <I> the identifier type
 */
public class AbstractCrudService<E, D, I> implements AbstractCrudBaseService<D, I>, AbstractLifeCycleHooks<E, D, I> {

    private final AbstractBaseRepository<E, I> repository;

    private final BaseMapper<E, D> mapper;

    /**
     * Constructs a new AbstractCrudService.
     * 
     * @param repository the base repository for the entity
     * @param mapper the mapper for converting between entity and DTO
     */
    public AbstractCrudService(AbstractBaseRepository<E, I> repository, BaseMapper<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public PageResponse<D> getAll(Pageable pageable, String q) {

        beforeGetAll(pageable, q);

        // Build dynamic specification using query string
        CommonSpecification<E> specification = new CommonSpecification<>(q);

        // Fetch filtered + paginated entities
        Page<E> e = repository.findAll(specification, pageable);

        afterGetAll(e);

        // Convert Spring Page into custom page model
        kals.com.core.model.Page page = PageUtil.convertRawPageToPageDomain(e);

        // Convert entities into DTOs
        List<D> d = mapper.toDtoList(e.getContent());


        // Build generic response
        return PageResponse.<D>builder().content(d).page(page).build();

    }

    @Override
    public D getById(I i) {

        beforeGet(i);

        Optional<E> e = repository.findById(i);

        if (e.isEmpty()) {
            throw new ResourceNotFoundException("AUTH_RES_404");
        }

        E saved = e.get();

        afterGet(saved);

        return mapper.toDto(saved);
    }

    @Override
    public D create(D d) {

        validation(d);

        validationBeforeCreate(d);

        beforeCreate(d);

        // Convert DTO -> Entity
        E e = mapper.toEntity(d);

        validationAfterCreate(e);

        // Save entity
        E saved = repository.save(e);

        afterCreate(e);

        // Convert Entity -> DTO
        return mapper.toDto(saved);
    }

    @Override
    public List<D> createAll(List<D> d) {
        // Convert DTOs -> Entities
        List<E> eList = mapper.toEntityList(d);

        // Save all entities
        List<E> saved = repository.saveAll(eList);

        // Convert back to DTOs

        return mapper.toDtoList(saved);
    }

    @Override
    public D update(D d, I i) {

        beforeUpdate(i, d);

        // Ensure the resource exists
        Optional<E> existing = repository.findById(i);

        if (existing.isEmpty()) {
            throw new ResourceNotFoundException("AUTH_RES_404");
        }

        // Convert DTO -> Entity
        E e = mapper.toEntity(d);

        E saved = repository.save(e);

        afterUpdate(saved);

        // Save updated entity
        return mapper.toDto(saved);
    }

    @Override
    public void delete(I i) {

        // Ensure the resource exists
        Optional<E> e = repository.findById(i);

        if (e.isEmpty()) {
            throw new ResourceNotFoundException("AUTH_RES_404");
        }
        beforeDelete(e.get());
        // Delete resource
        repository.deleteById(i);
        afterDelete(i);
    }

    @Override
    public void beforeCreate(D dto) {

    }

    @Override
    public void afterCreate(E entity) {

    }

    @Override
    public void beforeUpdate(I id, D dto) {

    }

    @Override
    public void afterUpdate(E entity) {

    }

    @Override
    public void beforeDelete(E entity) {

    }

    @Override
    public void afterDelete(I id) {

    }

    @Override
    public void beforeGet(I id) {

    }

    @Override
    public void afterGet(E entity) {

    }

    @Override
    public void beforeGetAll(Pageable pageable, String query) {

    }

    @Override
    public void afterGetAll(Page<E> page) {

    }

    @Override
    public void validationBeforeCreate(D d) {

    }

    @Override
    public void validationAfterCreate(E e) {

    }

    @Override
    public void validation(D d) {

    }
}
