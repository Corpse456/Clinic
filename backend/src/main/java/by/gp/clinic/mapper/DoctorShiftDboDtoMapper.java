package by.gp.clinic.mapper;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.DoctorShiftDbo;
import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.dto.DoctorShiftDto;
import by.gp.clinic.dto.ShiftTimingDto;
import by.gp.clinic.service.ShiftTimingService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class DoctorShiftDboDtoMapper implements AbstractDboDtoMapper<DoctorShiftDbo, DoctorShiftDto> {

    @Autowired
    protected ShiftTimingDboDtoMapper shiftTimingDboDtoMapper;

    @Autowired
    protected ShiftTimingService shiftTimingService;

    @Override
    @Mapping(target = "doctor", source = "doctorId", qualifiedByName = "buildEmptyDoctor")
    @Mapping(target = "shiftTiming", source = "shiftTiming", qualifiedByName = "getShiftTimingDbo")
    public abstract DoctorShiftDbo mapToDbo(DoctorShiftDto doctorShiftDto);

    @Override
    @Mapping(target = "doctorId", expression = "java(doctorShiftDbo.getDoctor().getId())")
    @Mapping(target = "shiftTiming",
            expression = "java(shiftTimingDboDtoMapper.mapToDto(doctorShiftDbo.getShiftTiming()))")
    public abstract DoctorShiftDto mapToDto(DoctorShiftDbo doctorShiftDbo);

    @Named("buildEmptyDoctor")
    protected DoctorDbo buildEmptyDoctor(Long doctorId) {
        return DoctorDbo.buildEmptyWithId(doctorId);
    }

    @Named("getShiftTimingDbo")
    protected ShiftTimingDbo getShiftTimingDbo(ShiftTimingDto shiftTiming) {
        return shiftTimingService.getShiftTimingDboOrCreate(
                shiftTiming.getStartTime(),
                shiftTiming.getEndTime(),
                shiftTiming.getShiftOrder());
    }
}
