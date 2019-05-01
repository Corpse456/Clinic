package by.gp.clinic.facade;

import by.gp.clinic.dto.PatientDto;
import by.gp.clinic.exception.EntityExistsException;
import by.gp.clinic.exception.EntityNotExistsException;
import by.gp.clinic.exception.PatientExistsException;
import by.gp.clinic.exception.PatientNotExistsException;
import by.gp.clinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientFacade {

    private final PatientService patientService;

    public Long createPatient(final PatientDto patient) throws PatientExistsException {
        if (patientService.isExistsByNameAndLastName(patient.getName(), patient.getLastName())) {
            throw new PatientExistsException(patient.getName(), patient.getLastName());
        }
        return patientService.post(patient);
    }

    public void removePatient(final Long id) throws PatientNotExistsException {
        checkPatientExists(id);
        patientService.delete(id);
    }

    public PatientDto getPatient(final Long id) throws PatientNotExistsException {
        checkPatientExists(id);
        return patientService.get(id);
    }

    private void checkPatientExists(final Long id) throws PatientNotExistsException {
        if (!patientService.isExists(id)) {
            throw new PatientNotExistsException(id);
        }
    }

    public List<PatientDto> findAll() {
        return patientService.findAll();
    }
}
