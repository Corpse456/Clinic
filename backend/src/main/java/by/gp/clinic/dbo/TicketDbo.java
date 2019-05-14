package by.gp.clinic.dbo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ticket")
@EqualsAndHashCode(callSuper = true)
public class TicketDbo extends AbstractDbo {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private PatientDbo patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private DoctorDbo doctor;

    private LocalDateTime dateTime;

    private Integer number;
}
