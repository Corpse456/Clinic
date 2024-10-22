package by.gp.clinic.dbo;

import by.gp.clinic.enumerated.ShiftOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "shift_timing")
@EqualsAndHashCode(callSuper = true)
public class ShiftTimingDbo extends AbstractDbo {

    private LocalTime startTime;

    private LocalTime endTime;

    @OneToOne
    @JoinColumn(name = "opposite_shift_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ShiftTimingDbo oppositeShift;

    @Enumerated(EnumType.STRING)
    private ShiftOrder shiftOrder;
}

