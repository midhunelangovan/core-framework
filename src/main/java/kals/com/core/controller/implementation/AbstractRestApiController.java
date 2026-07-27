package kals.com.core.controller.implementation;

import kals.com.core.controller.RestApiBaseController;
import kals.com.core.model.PageResponse;
import kals.com.core.service.AbstractCrudService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Generic REST API implementation.
 * <p>
 * E -> Entity
 * D -> DTO/Domain Model
 * I -> Identifier type (Long, UUID, String, etc.)
 * R -> Repository (supports CRUD + Specifications)
 * M -> Mapper (Entity <-> DTO)
 */
public abstract class AbstractRestApiController<E, D, I> implements RestApiBaseController<E, D, I> {

    private final AbstractCrudService<E, D, I> service;

    protected AbstractRestApiController(AbstractCrudService<E, D, I> service) {
        this.service = service;
    }

    @Override
    public PageResponse<D> getAll(Pageable pageable, @RequestParam(value = "q", required = false) String q) {
        return service.getAll(pageable, q);
    }

    @Override
    public ResponseEntity<D> getById(@PathVariable("id") I i) {
        D d = service.getById(i);
        return new ResponseEntity<>(d, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<D> create(@RequestBody @Validated D d) {
        D res = service.create(d);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<D>> createAll(@RequestBody List<D> d) {

        List<D> dList = service.createAll(d);
        return new ResponseEntity<>(dList, HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<D> update(@RequestBody D d, @PathVariable("id") I i) {
        D res = service.update(d, i);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<Void> delete(@PathVariable("id") I i) {
        service.delete(i);
        // HTTP 204 No Content
        return ResponseEntity.noContent().build();
    }
}