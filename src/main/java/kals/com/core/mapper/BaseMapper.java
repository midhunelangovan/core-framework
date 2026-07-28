package kals.com.core.mapper;

import java.util.List;

/**
 * Base mapper interface standardizing the conversion between Entities and DTOs.
 * Best used in combination with mapping libraries like MapStruct.
 *
 * @param <E> the entity type
 * @param <D> the DTO type
 */
public interface BaseMapper<E, D> {

    /**
     * Converts a DTO to an Entity.
     * @param dto the source DTO
     * @return the mapped entity
     */
    E toEntity(D dto);

    /**
     * Converts an Entity to a DTO.
     * @param entity the source entity
     * @return the mapped DTO
     */
    D toDto(E entity);

    /**
     * Converts a list of DTOs to a list of Entities.
     * @param dtos the source list of DTOs
     * @return the mapped list of entities
     */
    List<E> toEntityList(List<D> dtos);

    /**
     * Converts a list of Entities to a list of DTOs.
     * @param entities the source list of entities
     * @return the mapped list of DTOs
     */
    List<D> toDtoList(List<E> entities);

}
