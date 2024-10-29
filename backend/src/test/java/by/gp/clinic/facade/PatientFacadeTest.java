package by.gp.clinic.facade;

import by.gp.clinic.dto.PatientDto;
import by.gp.clinic.exception.PatientExistsException;
import by.gp.clinic.exception.PatientNotExistsException;
import by.gp.clinic.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class PatientFacadeTest {

    @InjectMocks
    private PatientFacade patientFacade;

    @Mock
    private PatientService patientService;

    @Test
    public void hirePatient() {
        assertThrows(PatientExistsException.class, () -> {
            doReturn(true).when(patientService).isExistsByNameAndLastName(any(), any());

            patientFacade.createPatient(new PatientDto());
        });
    }

    @Test
    public void firePatient() {
        assertThrows(PatientNotExistsException.class, () -> {
            doReturn(false).when(patientService).isExists(anyLong());

            patientFacade.removePatient(1L);
        });
    }

    @Test
    public void getPatient() {
        assertThrows(PatientNotExistsException.class, () -> {
            doReturn(false).when(patientService).isExists(anyLong());

            patientFacade.getPatient(1L);
        });
    }
}
