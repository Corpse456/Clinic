package by.gp.clinic.repository;

import by.gp.clinic.dbo.DoctorShiftDbo;
import by.gp.clinic.enums.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Transactional
public interface DoctorShiftRepository extends JpaRepository<DoctorShiftDbo, Long> {

    @Query("select s.shiftTiming.startTime from DoctorShiftDbo s where s.date = ?1 and s.doctor.speciality = ?2")
    List<LocalTime> findStartTimeByDateAndSpeciality(final LocalDate date, final Speciality speciality);
}