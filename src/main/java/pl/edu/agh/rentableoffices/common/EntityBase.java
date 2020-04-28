package pl.edu.agh.rentableoffices.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Class representing entity with Long ID
 */
@MappedSuperclass
@Getter
@Setter(AccessLevel.PROTECTED)
public class EntityBase {
    /**
     * Database ID. Uses generation strategy provided by database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    /**
     * Checks if record is persisted in the database
     * @return
     */
    public boolean isNew() {
        return id != null;
    }
}
