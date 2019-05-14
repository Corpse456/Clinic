package by.gp.clinic.facade;

import by.gp.clinic.service.ShiftTimingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShiftTimingFacade {

    private final ShiftTimingService shiftTimingService;

}
