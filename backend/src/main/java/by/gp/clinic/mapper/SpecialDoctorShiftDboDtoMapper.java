package by.gp.clinic.mapper;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.dbo.SpecialDoctorShiftDbo;
import by.gp.clinic.dbo.SpecialityDbo;
import by.gp.clinic.dto.ShiftTimingDto;
import by.gp.clinic.dto.SpecialDoctorShiftDto;
import by.gp.clinic.service.ShiftTimingService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Lookup;

@Mapper(componentModel = "spring")
public interface SpecialDoctorShiftDboDtoMapper extends AbstractDboDtoMapper<SpecialDoctorShiftDbo, SpecialDoctorShiftDto> {

    @Lookup
    default ShiftTimingDboDtoMapper shiftTimingDboDtoMapper() {
        return null;
    }

    @Lookup
    default ShiftTimingService shiftTimingService() {
        return null;
    }

    @Override
    @Mapping(target = "doctor", source = "doctorId", qualifiedByName = "getDoctorDbo")
    @Mapping(target = "speciality", source = "specialityId", qualifiedByName = "getSpecialityDbo")
    @Mapping(target = "shiftTiming", source = "shiftTiming", qualifiedByName = "getShiftTimingDbo")
    SpecialDoctorShiftDbo mapToDbo(SpecialDoctorShiftDto sourceDto);

    @Override
    @Mapping(target = "doctorId", source = "doctor", qualifiedByName = "getDoctorId")
    @Mapping(target = "specialityId", source = "speciality", qualifiedByName = "getSpecialityId")
    @Mapping(target = "shiftTiming", expression = "java(shiftTimingDboDtoMapper().mapToDto(sourceDbo.getShiftTiming()))")
    SpecialDoctorShiftDto mapToDto(SpecialDoctorShiftDbo sourceDbo);

    @Named("getDoctorDbo")
    default DoctorDbo getDoctorDbo(Long id) {
        return id != null ? DoctorDbo.buildEmptyWithId(id) : null;
    }

    @Named("getSpecialityDbo")
    default SpecialityDbo getSpecialityDbo(Long id) {
        return id != null ? SpecialityDbo.buildEmptyWithId(id) : null;
    }

    @Named("getDoctorId")
    default Long getDoctorId(DoctorDbo doctorDbo) {
        return doctorDbo != null ? doctorDbo.getId() : null;
    }

    @Named("getSpecialityId")
    default Long getSpecialityId(SpecialityDbo specialityDbo) {
        return specialityDbo != null ? specialityDbo.getId() : null;
    }

    @Named("getShiftTimingDbo")
    default ShiftTimingDbo getShiftTimingDbo(ShiftTimingDto shiftTiming) {
        return shiftTimingService().getShiftTimingDboOrCreate(
                shiftTiming.getStartTime(),
                shiftTiming.getEndTime(),
                shiftTiming.getShiftOrder());
    }
}
