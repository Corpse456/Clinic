package by.gp.clinic.facade;

import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.exception.DoctorExistsException;
import by.gp.clinic.exception.DoctorNotExistsException;
import by.gp.clinic.exception.ShiftTimingNotExistsException;
import by.gp.clinic.service.DoctorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class DoctorFacadeTest {

    @InjectMocks
    private DoctorFacade doctorFacade;

    @Mock
    private DoctorService doctorService;

    @Test(expected = DoctorExistsException.class)
    public void hireDoctor() throws DoctorExistsException, ShiftTimingNotExistsException {
        doReturn(true).when(doctorService).isExistsByNameAndLastName(any(), any());

        doctorFacade.hireDoctor(new DoctorDto());
    }

    @Test(expected = DoctorNotExistsException.class)
    public void fireDoctor() throws DoctorNotExistsException {
        doReturn(false).when(doctorService).isExists(anyLong());

        doctorFacade.fireDoctor(1L);
    }

    @Test(expected = DoctorNotExistsException.class)
    public void getDoctor() throws DoctorNotExistsException {
        doReturn(false).when(doctorService).isExists(anyLong());

        doctorFacade.getDoctor(1L);
    }
}
