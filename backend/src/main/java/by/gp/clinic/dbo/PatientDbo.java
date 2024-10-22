package by.gp.clinic.dbo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "patient")
@EqualsAndHashCode(callSuper = true)
public class PatientDbo extends ManDbo {

    public static PatientDbo buildEmptyWithId(final Long id) {
        final var patient = new PatientDbo();
        patient.setId(id);
        return patient;
    }
}
