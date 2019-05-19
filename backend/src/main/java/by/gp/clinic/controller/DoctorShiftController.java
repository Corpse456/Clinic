package by.gp.clinic.controller;

import by.gp.clinic.dto.DoctorShiftDto;
import by.gp.clinic.dto.PageDto;
import by.gp.clinic.dto.ShiftTimingDto;
import by.gp.clinic.dto.SpecialDoctorShiftDto;
import by.gp.clinic.facade.DoctorShiftFacade;
import by.gp.clinic.search.DoctorShiftSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shift")
@Api(tags = "Doctor shift Controller",
    value = "API methods for work with doctor shifts")
public class DoctorShiftController {

    private final DoctorShiftFacade facade;

    @PostMapping
    @ApiOperation(value = "Post doctor shift for date")
    public void postShiftForDate(@RequestBody @Validated final DoctorShiftDto doctorShift) {
        facade.postShiftForDate(doctorShift);
    }

    @PostMapping(value = "/special")
    @ApiOperation(value = "Post special doctor shift for date")
    public void postSpecialShiftForDate(@RequestBody @Validated final SpecialDoctorShiftDto specialDoctorShiftDto) {
        facade.postSpecialShiftForDate(specialDoctorShiftDto);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get doctor shifts by doctor id")
    public Map<LocalDate, ShiftTimingDto> getDoctorShiftsByDoctorId(@PathVariable("id") final Long id) {
        return facade.getDoctorShift(id);
    }

    @PostMapping(value = "/search")
    @ApiOperation(value = "Get doctor shifts by doctor id")
    public PageDto<DoctorShiftDto> getDoctorShifts(
        @RequestBody @Validated final DoctorShiftSearchRequest searchRequest) {
        return facade.getDoctorShifts(searchRequest);
    }
}
