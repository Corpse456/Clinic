package by.gp.clinic.dbo;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
