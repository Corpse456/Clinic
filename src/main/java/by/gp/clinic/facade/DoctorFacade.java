package by.gp.clinic.facade;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.exception.DoctorExistsException;
import by.gp.clinic.exception.DoctorNotExistsException;
import by.gp.clinic.service.DoctorService;
import by.gp.clinic.service.DoctorShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorFacade {

    private final DoctorService doctorService;
    private final DoctorShiftService doctorShiftService;

    public Long hireDoctor(final DoctorDto doctor) throws DoctorExistsException {
        if (doctorService.isExistsByNameAndLastName(doctor.getName(), doctor.getLastName())) {
            throw new DoctorExistsException(doctor.getName(), doctor.getLastName());
        }
        final DoctorDbo savedDoctor = doctorService.post(doctor);
        doctorShiftService.addNewDoctorToTimeTable(savedDoctor);
        return savedDoctor.getId();
    }

    public void fireDoctor(final Long id) throws DoctorNotExistsException {
        checkDoctorExists(id);
        doctorService.delete(id);
    }

    public DoctorDto getDoctor(final Long id) throws DoctorNotExistsException {
        checkDoctorExists(id);
        return doctorService.get(id);
    }

    private void checkDoctorExists(final Long id) throws DoctorNotExistsException {
        if (!doctorService.isExists(id)) {
            throw new DoctorNotExistsException(id);
        }
    }

    public List<DoctorDto> findAll() {
        return doctorService.findAll();
    }
}
