package by.gp.clinic.dbo;

import by.gp.clinic.enumerated.Speciality;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "doctor")
@EqualsAndHashCode(callSuper = true)
public class DoctorDbo extends ManDbo {

    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    public static DoctorDbo buildEmptyWithId(final Long id) {
        final DoctorDbo patient = new DoctorDbo();
        patient.setId(id);
        return patient;
    }
}
