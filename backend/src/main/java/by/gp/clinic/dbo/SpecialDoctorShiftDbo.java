package by.gp.clinic.dbo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.DayOfWeek;

@Data
@Entity
@Table(name = "special_doctor_shift")
@EqualsAndHashCode(callSuper = true)
public class SpecialDoctorShiftDbo extends AbstractDbo {

    @OneToOne
    @JoinColumn(name = "doctor_id")
    @Nullable
    private DoctorDbo doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speciality_id")
    @Nullable
    private SpecialityDbo speciality;

    @Enumerated(EnumType.STRING)
    private DayOfWeek day;

    @ManyToOne
    @JoinColumn(name = "shift_timing_id")
    private ShiftTimingDbo shiftTiming;
}

