package by.gp.clinic.dbo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "doctor_shift")
@EqualsAndHashCode(callSuper = true)
public class DoctorShiftDbo extends AbstractDbo {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private DoctorDbo doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shift_timing_id")
    private ShiftTimingDbo shiftTiming;

    private LocalDate date;
}
