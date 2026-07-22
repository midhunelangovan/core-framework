package kals.com.core.service;

import kals.com.core.model.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AbstractBaseService<D, I> {

    PageResponse<D> getAll(Pageable pageable, String q);

    D getById(I i);

    D create(D d);

    List<D> createAll(List<D> d);

    D update(D d, I i);

    void delete(I i);


}
