package by.gp.clinic.validation;

import by.gp.clinic.annotation.SpecialDoctorShift;
import by.gp.clinic.dto.SpecialDoctorShiftDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static by.gp.clinic.util.ValidationUtils.addMessageToContext;
import static by.gp.clinic.util.ValidationUtils.checkNotNullFields;

public class SpecialDoctorShiftValidator implements ConstraintValidator<SpecialDoctorShift, SpecialDoctorShiftDto> {

    private static final String MESSAGE = "one of property must be not null (doctorId / speciality)";

    @Override
    public boolean isValid(final SpecialDoctorShiftDto value, final ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        boolean valid = checkNotNullFields(value, context);

        if (value.getDoctorId() == null && value.getSpeciality() == null) {
            addMessageToContext("doctorId", MESSAGE, context);
            valid = false;
        }

        return valid;
    }
}
