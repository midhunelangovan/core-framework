package kals.com.core.service;

import kals.com.core.model.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RestApiService<E, D, I, R, M> {


    ResponseEntity<PageResponse<D>> getAll(Pageable pageable, String q);

    ResponseEntity<D> getById(I i);

    ResponseEntity<D> create(D d);

    ResponseEntity<List<D>> createAll(List<D> d);

    ResponseEntity<D> update(D d, I i);

    ResponseEntity<Void> delete(I i);


}
