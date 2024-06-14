package by.gp.clinic.dbo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
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
