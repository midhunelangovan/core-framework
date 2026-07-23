package kals.com.core.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface AbstractLifeCycleHooks<E, D, I> {

    void beforeCreate(D dto);

    void afterCreate(E entity);

    void beforeUpdate(I id, D dto);

    void afterUpdate(E entity);

    void beforeDelete(E entity);

    void afterDelete(I id);

    void beforeGet(I id);

    void afterGet(E entity);

    void beforeGetAll(Pageable pageable, String query);

    void afterGetAll(Page<E> page);

    void validationBeforeCreate(D d);

    void validationAfterCreate(E e);

    void validation(D d);
}
