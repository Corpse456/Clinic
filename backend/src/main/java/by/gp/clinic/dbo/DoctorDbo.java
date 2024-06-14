package by.gp.clinic.dbo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "doctor")
@EqualsAndHashCode(callSuper = true)
public class DoctorDbo extends ManDbo {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speciality_id")
    private SpecialityDbo speciality;

    private String specialIdentifier;

    public static DoctorDbo buildEmptyWithId(final Long id) {
        final var patient = new DoctorDbo();
        patient.setId(id);
        return patient;
    }
}
