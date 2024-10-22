package by.gp.clinic.dbo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.time.DayOfWeek;

@Getter
@Setter
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

