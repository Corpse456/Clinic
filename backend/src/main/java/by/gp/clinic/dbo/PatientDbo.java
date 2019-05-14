package by.gp.clinic.dbo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "patient")
@EqualsAndHashCode(callSuper = true)
public class PatientDbo extends ManDbo {

    public static PatientDbo buildEmptyWithId(final Long id) {
        final PatientDbo patient = new PatientDbo();
        patient.setId(id);
        return patient;
    }
}
