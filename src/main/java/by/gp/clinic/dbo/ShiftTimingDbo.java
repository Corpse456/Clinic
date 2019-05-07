package by.gp.clinic.dbo;

import by.gp.clinic.enums.ShiftOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Time;
import java.time.LocalTime;

@Data
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

