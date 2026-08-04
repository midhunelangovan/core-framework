package io.kals.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Base repository interface providing standard CRUD and JPA Specification capabilities.
 * All domain repositories should extend this interface.
 *
 * @param <E> the entity type
 * @param <I> the identifier type
 */
@NoRepositoryBean
public interface AbstractBaseRepository<E, I> extends JpaRepository<E, I>, JpaSpecificationExecutor<E> {
}
