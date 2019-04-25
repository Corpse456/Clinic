package by.gp.clinic.facade;

import by.gp.clinic.dto.PatientDto;
import by.gp.clinic.exception.PatientExistsException;
import by.gp.clinic.exception.PatientNotExistsException;
import by.gp.clinic.service.PatientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class PatientFacadeTest {

    @InjectMocks
    private PatientFacade patientFacade;

    @Mock
    private PatientService patientService;

    @Test(expected = PatientExistsException.class)
    public void hireDoctor() throws PatientExistsException {
        doReturn(true).when(patientService).isPatientExists(any(), any());

        patientFacade.createPatient(new PatientDto());
    }

    @Test(expected = PatientNotExistsException.class)
    public void fireDoctor() throws PatientNotExistsException {
        doReturn(false).when(patientService).isExists(anyLong());

        patientFacade.removePatient(1L);
    }

    @Test(expected = PatientNotExistsException.class)
    public void getDoctor() throws PatientNotExistsException {
        doReturn(false).when(patientService).isExists(anyLong());

        patientFacade.getPatient(1L);
    }
}