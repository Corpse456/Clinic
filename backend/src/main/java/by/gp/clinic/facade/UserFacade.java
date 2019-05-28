package by.gp.clinic.facade;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.PatientDbo;
import by.gp.clinic.dbo.UserDbo;
import by.gp.clinic.dto.CredentialsDto;
import by.gp.clinic.enumerated.UserRole;
import by.gp.clinic.exception.DoctorNotExistsException;
import by.gp.clinic.exception.PatientNotExistsException;
import by.gp.clinic.exception.UserExistsException;
import by.gp.clinic.service.DoctorService;
import by.gp.clinic.service.PatientService;
import by.gp.clinic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final PasswordEncoder passwordEncoder;

    public UserDbo createUser(final CredentialsDto credentials) throws DoctorNotExistsException,
                                                                       PatientNotExistsException, UserExistsException {
        if (userService.existsByAlias(credentials.getAlias())) {
            throw new UserExistsException(credentials.getAlias());
        }
        final UserDbo userDbo = new UserDbo();
        userDbo.setAlias(credentials.getAlias());
        userDbo.setPassword(passwordEncoder.encode(credentials.getPassword()));

        if (credentials.getSpecialIdentifier() != null) {
            final DoctorDbo doctor = doctorService
                .getDoctor(credentials.getName(), credentials.getLastName(), credentials.getSpecialIdentifier())
                .orElseThrow(() -> new DoctorNotExistsException(credentials.getName(), credentials.getLastName(),
                                                                credentials.getSpecialIdentifier()));
            userDbo.setDoctor(doctor);
            userDbo.setRole(UserRole.DOCTOR);
        } else {
            final PatientDbo patient = patientService
                .getPatient(credentials.getName(), credentials.getLastName())
                .orElseThrow(() -> new PatientNotExistsException(credentials.getName(), credentials.getLastName()));
            userDbo.setPatient(patient);
            userDbo.setRole(UserRole.USER);
        }

        return userService.save(userDbo);
    }
}
