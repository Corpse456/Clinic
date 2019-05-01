package by.gp.clinic.dbo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "shift_timing")
public class ShiftTimingDbo extends AbstractDbo {

    private LocalTime startTime;

    private LocalTime endTime;
}
