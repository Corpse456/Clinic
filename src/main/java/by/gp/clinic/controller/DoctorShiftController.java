package by.gp.clinic.controller;

import by.gp.clinic.dto.ShiftTimingDto;
import by.gp.clinic.facade.DoctorShiftFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shift")
@Api(tags = "Doctor shift Controller",
    description = "API methods for work with doctor shifts")
public class DoctorShiftController {

    private final DoctorShiftFacade facade;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get doctor shifts")
    public Map<LocalDate, ShiftTimingDto> getDoctorShifts(@PathVariable("id") final Long id) {
        return facade.getDoctorShift(id);
    }
}
