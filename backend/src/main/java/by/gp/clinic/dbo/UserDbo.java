package by.gp.clinic.dbo;

import by.gp.clinic.enumerated.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user")
public class UserDbo extends AbstractDbo {

    @NotNull
    private String alias;

    @NotNull
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private DoctorDbo doctor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private PatientDbo patient;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @NotNull
    private boolean enabled = true;
}
