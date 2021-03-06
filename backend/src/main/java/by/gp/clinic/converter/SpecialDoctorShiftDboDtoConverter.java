package by.gp.clinic.converter;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.SpecialDoctorShiftDbo;
import by.gp.clinic.dbo.SpecialityDbo;
import by.gp.clinic.dto.ShiftTimingDto;
import by.gp.clinic.dto.SpecialDoctorShiftDto;
import by.gp.clinic.service.ShiftTimingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecialDoctorShiftDboDtoConverter
    extends AbstractDboDtoConverter<SpecialDoctorShiftDbo, SpecialDoctorShiftDto> {

    private final ShiftTimingDboDtoConverter shiftTimingDboDtoConverter;
    private final ShiftTimingService shiftTimingService;

    @Override
    protected SpecialDoctorShiftDto constructDto() {
        return new SpecialDoctorShiftDto();
    }

    @Override
    protected SpecialDoctorShiftDbo constructDbo() {
        return new SpecialDoctorShiftDbo();
    }

    @Override
    protected String[] getIgnoreProperties() {
        return new String[]{"shiftTiming", "speciality"};
    }

    @Override
    protected void convertComplexFieldsForDto(final SpecialDoctorShiftDbo sourceDbo,
                                              final SpecialDoctorShiftDto targetDto) {
        if (sourceDbo.getDoctor() != null) {
            targetDto.setDoctorId(sourceDbo.getDoctor().getId());
        }
        targetDto.setShiftTiming(shiftTimingDboDtoConverter.convertToDto(sourceDbo.getShiftTiming()));
        if (sourceDbo.getSpeciality() != null) {
            targetDto.setSpecialityId(sourceDbo.getSpeciality().getId());
        }
    }

    @Override
    protected void convertComplexFieldsForDbo(final SpecialDoctorShiftDto sourceDto,
                                              final SpecialDoctorShiftDbo targetDbo) {
        if (sourceDto.getDoctorId() != null) {
            targetDbo.setDoctor(DoctorDbo.buildEmptyWithId(sourceDto.getDoctorId()));
        }
        final ShiftTimingDto shiftTiming = sourceDto.getShiftTiming();
        targetDbo.setShiftTiming(shiftTimingService.getShiftTimingDboOrCreate(shiftTiming.getStartTime(),
                                                                              shiftTiming.getEndTime(),
                                                                              shiftTiming.getShiftOrder()));
        if (sourceDto.getSpecialityId() != null) {
            targetDbo.setSpeciality(SpecialityDbo.buildEmptyWithId(sourceDto.getSpecialityId()));
        }
    }
}
