package by.gp.clinic.converter;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.DoctorShiftDbo;
import by.gp.clinic.dto.DoctorShiftDto;
import by.gp.clinic.service.ShiftTimingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorShiftDboDtoConverter extends AbstractDboDtoConverter<DoctorShiftDbo, DoctorShiftDto> {

    private final ShiftTimingDboDtoConverter shiftTimingDboDtoConverter;
    private final ShiftTimingService shiftTimingService;

    @Override
    protected DoctorShiftDto constructDto() {
        return new DoctorShiftDto();
    }

    @Override
    protected DoctorShiftDbo constructDbo() {
        return new DoctorShiftDbo();
    }

    @Override
    protected String[] getIgnoreProperties() {
        return new String[]{"shiftTiming"};
    }

    @Override
    protected void convertComplexFieldsForDto(final DoctorShiftDbo sourceDbo, final DoctorShiftDto targetDto) {
        targetDto.setDoctorId(sourceDbo.getDoctor().getId());
        targetDto.setShiftTiming(shiftTimingDboDtoConverter.convertToDto(sourceDbo.getShiftTiming()));
    }

    @Override
    protected void convertComplexFieldsForDbo(final DoctorShiftDto sourceDto, final DoctorShiftDbo targetDbo) {
        targetDbo.setDoctor(DoctorDbo.buildEmptyWithId(sourceDto.getDoctorId()));

        final var shiftTiming = sourceDto.getShiftTiming();
        targetDbo.setShiftTiming(shiftTimingService.getShiftTimingDboOrCreate(shiftTiming.getStartTime(),
                                                                              shiftTiming.getEndTime(),
                                                                              shiftTiming.getShiftOrder()));
    }
}
