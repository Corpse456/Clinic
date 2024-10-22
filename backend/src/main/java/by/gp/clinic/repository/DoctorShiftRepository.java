package by.gp.clinic.repository;

import by.gp.clinic.dbo.DoctorShiftDbo;
import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.enumerated.ShiftOrder;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Transactional
public interface DoctorShiftRepository extends CustomRepository<DoctorShiftDbo, Long> {

  @Query("select shift.shiftOrder "
      + "from DoctorShiftDbo s "
      + "join fetch ShiftTimingDbo shift on s.shiftTiming.id = shift.id "
      + "join fetch DoctorDbo d on s.doctor.id= d.id "
      + "where s.date = ?1 "
      + "and d.speciality.id = ?2")
  List<ShiftOrder> findShiftOrdersByDateAndSpeciality(final LocalDate date, final Long specialityId);

  @Query("select shift "
      + "from DoctorShiftDbo s "
      + "join fetch ShiftTimingDbo shift on s.shiftTiming.id = shift.id "
      + "where s.doctor.id = ?1 "
      + "and s.date = ?2")
  ShiftTimingDbo getShiftTimingByDoctorIdAndDate(final Long id, final LocalDate date);

  boolean existsByDoctorIdAndDate(final Long id, final LocalDate date);

  Optional<DoctorShiftDbo> getByDoctorIdAndDate(final Long id, final LocalDate date);

  List<DoctorShiftDbo> getAllByDoctorId(final Long id);

  @Query("select count(s) from DoctorShiftDbo s " +
      "join fetch ShiftTimingDbo shift " +
      "on s.shiftTiming.id = shift.id " +
      "where s.date = ?2 " +
      "and s.doctor.id = ?1 " +
      "and shift.startTime <= ?3 " +
      "and shift.endTime > ?3")
  int countByDoctorIdAndDateTime(Long id, LocalDate date, LocalTime time);
}
