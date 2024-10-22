package by.gp.clinic.dbo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "speciality")
@EqualsAndHashCode(callSuper = true)
public class SpecialityDbo extends AbstractDbo {

    private String name;

    public static SpecialityDbo buildEmptyWithId(final Long id) {
        final var speciality = new SpecialityDbo();
        speciality.setId(id);
        return speciality;
    }
}
