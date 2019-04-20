package by.gp.clinic.facade;

import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.exception.DoctorExistsException;
import by.gp.clinic.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DoctorFacade {

    private final DoctorService doctorService;

    @Transactional
    public void hireDoctor(final DoctorDto doctor) throws DoctorExistsException {
        if (doctorService.isDoctorExists(doctor.getName(), doctor.getLastName())) {
            throw new DoctorExistsException(doctor.getName(), doctor.getLastName());
        }
        doctorService.hireDoctor(doctor);
    }
}
