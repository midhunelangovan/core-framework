package kals.com.core.service;

import kals.com.core.exception.ResourceNotFoundException;
import kals.com.core.mapper.BaseMapper;
import kals.com.core.model.PageResponse;
import kals.com.core.specification.CommonSpecification;
import kals.com.core.utility.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public class AbstractServiceImpl
        <
                E,
                D,
                I,
                R extends JpaRepository<E, I> & JpaSpecificationExecutor<E>,
                M extends BaseMapper<E, D>
                >
        implements AbstractBaseService<D, I> {

    @Autowired
    R repository;

    @Autowired
    M mapper;

    @Override
    public PageResponse<D> getAll(Pageable pageable, String q) {
        // Build dynamic specification using query string
        CommonSpecification<E> specification = new CommonSpecification<>(q);

        // Fetch filtered + paginated entities
        Page<E> e = repository.findAll(specification, pageable);
        // Convert Spring Page into custom page model
        kals.com.core.model.Page page = PageUtil.convertRawPageToPageDomain(e);

        // Convert entities into DTOs
        List<D> d = mapper.toDtoList(e.getContent());

        // Build generic response
        PageResponse<D> pageResponse = PageResponse.<D>builder().content(d).page(page).build();
        return pageResponse;

    }

    @Override
    public D getById(I i) {
        Optional<E> e = repository.findById(i);

        if (e.isEmpty()) {
            throw new ResourceNotFoundException("AUTH_RES_404");
        }

        D d = mapper.toDto(e.get());

        return d;
    }

    @Override
    public D create(D d) {
        // Convert DTO -> Entity
        E e = mapper.toEntity(d);

        // Save entity
        E saved = repository.save(e);

        // Convert Entity -> DTO
        D res = mapper.toDto(saved);
        return res;
    }

    @Override
    public List<D> createAll(List<D> d) {
        // Convert DTOs -> Entities
        List<E> eList = mapper.toEntityList(d);

        // Save all entities
        List<E> saved = repository.saveAll(eList);

        // Convert back to DTOs
        List<D> dList = mapper.toDtoList(saved);

        return dList;
    }

    @Override
    public D update(D d, I i) {

        // Ensure the resource exists
        Optional<E> existing = repository.findById(i);

        if (existing.isEmpty()) {
            throw new ResourceNotFoundException("AUTH_RES_404");
        }

        // Convert DTO -> Entity
        E e = mapper.toEntity(d);

        // Save updated entity
        D res = mapper.toDto(repository.save(e));
        return res;
    }

    @Override
    public void delete(I i) {
        // Ensure the resource exists
        Optional<E> e = repository.findById(i);

        if (e.isEmpty()) {
            throw new ResourceNotFoundException("AUTH_RES_404");
        }

        // Delete resource
        repository.deleteById(i);
    }

}
