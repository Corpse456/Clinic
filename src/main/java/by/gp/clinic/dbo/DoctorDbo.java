package by.gp.clinic.dbo;

import by.gp.clinic.enums.Specialty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "doctor")
public class DoctorDbo extends ManDbo {

    @Enumerated(EnumType.STRING)
    private Specialty specialty;
}
