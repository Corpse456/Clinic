package by.gp.clinic.mock;

import by.gp.clinic.dto.UserDto;
import by.gp.clinic.enumerated.UserRole;

public class UserMock {

    private static final String ALIAS = "Alias";
    private static final String NAME = "Theodore";
    private static final String LAST_NAME = "Aldridge";
    private static final UserRole USER = UserRole.USER;

    public static UserDto getUserPatientDtoMock() {
        final var user = new UserDto();

        user.setAlias(ALIAS);
        user.setName(NAME);
        user.setLastName(LAST_NAME);
        user.setPatientId(1L);
        user.setRole(USER);
        user.setEnabled(true);
        user.setId(1L);

        return user;
    }
}
