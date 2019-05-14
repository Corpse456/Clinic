package by.gp.clinic.service;

import by.gp.clinic.exception.ShiftTimingNotExistsException;
import by.gp.clinic.repository.ShiftTimingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ShiftTimingServiceTest {

    private static final LocalTime WRONG_TIME = LocalTime.of(0, 0);

    @InjectMocks
    private ShiftTimingService service;
    @Mock
    private ShiftTimingRepository repository;

    @Test(expected = ShiftTimingNotExistsException.class)
    public void getShiftTimingDboTest() throws ShiftTimingNotExistsException {
        doReturn(Optional.empty()).when(repository).getByStartTimeAndEndTime(any(), any());
        service.getShiftTimingDbo(WRONG_TIME, WRONG_TIME);
    }
}