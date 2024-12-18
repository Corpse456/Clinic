package by.gp.clinic.validation;

import by.gp.clinic.annotation.SpecialDoctorShift;
import by.gp.clinic.dto.SpecialDoctorShiftDto;
import jakarta.validation.ConstraintValidatorContext;

import static by.gp.clinic.util.ValidationUtils.addMessageToContext;

public class SpecialDoctorShiftValidator extends AbstractValidator<SpecialDoctorShift, SpecialDoctorShiftDto> {

    private static final String MESSAGE = "one of property must be not null (doctorId / speciality)";

    @Override
    protected boolean checkValid(final SpecialDoctorShiftDto value, final ConstraintValidatorContext context) {
        var valid = true;

        if (value.getDoctorId() == null && value.getSpecialityId() == null) {
            addMessageToContext("doctorId", SpecialDoctorShiftValidator.MESSAGE, context);
            valid = false;
        }

        return valid;
    }

    @Override
    protected String getClassName() {
        return "Special Doctor Shift";
    }
}
