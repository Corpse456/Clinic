package by.gp.clinic.service;

import by.gp.clinic.exception.ShiftTimingNotExistsException;
import by.gp.clinic.repository.ShiftTimingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class ShiftTimingServiceTest {

    private static final LocalTime WRONG_TIME = LocalTime.of(0, 0);

    @InjectMocks
    private ShiftTimingService service;

    @Mock
    private ShiftTimingRepository repository;

    @Test
    public void getShiftTimingDboTest() throws ShiftTimingNotExistsException {
        assertThrows(ShiftTimingNotExistsException.class, () -> {
            doReturn(Optional.empty()).when(repository).getByStartTimeAndEndTime(any(), any());
            service.getShiftTimingDbo(WRONG_TIME, WRONG_TIME);
        });
    }
}
