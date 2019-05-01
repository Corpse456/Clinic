package by.gp.clinic.dbo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "shift_timing")
@EqualsAndHashCode(callSuper = true)
public class ShiftTimingDbo extends AbstractDbo {

    private LocalTime startTime;

    private LocalTime endTime;
}
