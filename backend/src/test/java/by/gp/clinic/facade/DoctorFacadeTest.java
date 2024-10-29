package by.gp.clinic.facade;

import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.exception.DoctorExistsException;
import by.gp.clinic.exception.DoctorNotExistsException;
import by.gp.clinic.service.DoctorService;
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
public class DoctorFacadeTest {

    @InjectMocks
    private DoctorFacade doctorFacade;

    @Mock
    private DoctorService doctorService;

    @Test
    public void hireDoctor() {
        assertThrows(DoctorExistsException.class, () -> {
            doReturn(true).when(doctorService).isExistsByNameAndLastName(any(), any());

            doctorFacade.hireDoctor(new DoctorDto());
        });
    }

    @Test
    public void fireDoctor() throws DoctorNotExistsException {
        assertThrows(DoctorNotExistsException.class, () -> {
            doReturn(false).when(doctorService).isExists(anyLong());

            doctorFacade.fireDoctor(1L);
        });
    }

    @Test
    public void getDoctor() throws DoctorNotExistsException {
        assertThrows(DoctorNotExistsException.class, () -> {
            doReturn(false).when(doctorService).isExists(anyLong());

            doctorFacade.getDoctor(1L);
        });
    }
}
