package by.gp.clinic.repository;

import by.gp.clinic.dbo.ShiftTimingDbo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.Optional;

@Transactional
public interface ShiftTimingRepository extends CustomRepository<ShiftTimingDbo, Long> {

    Optional<ShiftTimingDbo> getByStartTimeAndEndTime(final LocalTime startTime, final LocalTime endTime);
}