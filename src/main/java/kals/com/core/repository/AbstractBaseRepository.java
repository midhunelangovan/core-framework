package kals.com.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractBaseRepository<E, I> extends JpaRepository<E, I>, JpaSpecificationExecutor<E> {
}