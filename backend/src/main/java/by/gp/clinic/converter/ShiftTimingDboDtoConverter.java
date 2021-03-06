package by.gp.clinic.converter;

import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.dto.ShiftTimingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShiftTimingDboDtoConverter extends AbstractDboDtoConverter<ShiftTimingDbo, ShiftTimingDto> {

    @Override
    protected ShiftTimingDto constructDto() {
        return new ShiftTimingDto();
    }

    @Override
    protected ShiftTimingDbo constructDbo() {
        return new ShiftTimingDbo();
    }

    @Override
    protected String[] getIgnoreProperties() {
        return new String[]{"oppositeShift"};
    }
}
