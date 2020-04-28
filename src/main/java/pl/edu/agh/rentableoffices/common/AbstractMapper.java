package pl.edu.agh.rentableoffices.common;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Abstract assembler interface for converting domain model to DTOs
 * @param <T> Entity class
 * @param <D> DTO class
 */
public interface AbstractMapper<T, D> {
    /**
     * Convert Domain model to DTO object
     * @param entity JPA entity
     * @return DTO
     */
    D toDto(T entity);

    /**
     * Method for converting collection of entities to List of DTOs. If collection is null, empty collection is returned
     * @param entities collection of JPA entities
     * @return List of DTOs
     */
    default List<D> toDtoList(Collection<T> entities) {
        if(entities == null) {
            return Collections.emptyList();
        }
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    /**
     * Method for converting collection of entities to List of DTOs. If collection is null, empty list is returned
     * @param entities collection of JPA entities
     * @return List of DTOs
     */
    default List<D> toDtoList(Iterable<T> entities) {
        if(entities == null) {
            return Collections.emptyList();
        }
        return StreamSupport.stream(entities.spliterator(), false).map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Method for converting collection of entities to Set of DTOs. If collection is null, empty set is returned
     * @param entities collection of JPA entities
     * @return Set of DTOs
     */
    default Set<D> toDtoSet(Collection<T> entities) {
        if(entities == null) {
            return Collections.emptySet();
        }
        return entities.stream().map(this::toDto).collect(Collectors.toSet());
    }

    /**
     * Method for converting collection of entities to Set of DTOs. If collection is null, empty set is returned
     * @param entities collection of JPA entities
     * @return List of DTOs
     */
    default Set<D> toDtoSet(Iterable<T> entities) {
        if(entities == null) {
            return Collections.emptySet();
        }
        return StreamSupport.stream(entities.spliterator(), false).map(this::toDto)
                .collect(Collectors.toSet());
    }
}