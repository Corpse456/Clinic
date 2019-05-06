package by.gp.clinic.facade;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.DoctorShiftDbo;
import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.dbo.SpecialDoctorShiftDbo;
import by.gp.clinic.service.DoctorService;
import by.gp.clinic.service.DoctorShiftService;
import by.gp.clinic.service.ShiftTimingService;
import by.gp.clinic.service.SpecialDoctorShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftTimingFacade {

    private final ShiftTimingService shiftTimingService;
    private final DoctorShiftService doctorShiftService;
    private final SpecialDoctorShiftService specialDoctorShiftService;
    private final DoctorService doctorService;

    public void addNewDoctorToTimeTable(final DoctorDbo doctor) {
        final LocalDate nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));

        final DoctorShiftDbo doctorShift = new DoctorShiftDbo();
        doctorShift.setDate(nextMonday);
        doctorShift.setDoctor(doctor);

        final List<SpecialDoctorShiftDbo> specialShifts =
            specialDoctorShiftService.findSpecialShifts(doctor.getId(), doctor.getSpeciality()).stream().map();
        final List<LocalTime> shiftsForDay = doctorShiftService.getStartTimeForDay(nextMonday, doctor.getSpeciality());
        final ShiftTimingDbo preferredShift = shiftTimingService.getPreferredShift(shiftsForDay);
        doctorShift.setShiftTiming(preferredShift);
    }
}
