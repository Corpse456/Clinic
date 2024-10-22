package by.gp.clinic.dbo;

import by.gp.clinic.enumerated.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
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
