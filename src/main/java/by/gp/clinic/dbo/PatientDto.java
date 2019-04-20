package by.gp.clinic.dbo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "patient")
public class PatientDto extends ManDbo {
}
