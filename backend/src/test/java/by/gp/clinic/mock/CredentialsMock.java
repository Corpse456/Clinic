package by.gp.clinic.mock;

import by.gp.clinic.dto.CredentialsDto;

public class CredentialsMock {

    private static final String ALIAS = "Alias";
    private static final String NAME = "Theodore";
    private static final String LAST_NAME = "Aldridge";
    private static final String PASSWORD = "Password1";
    private static final String ADMIN_NAME = "Theodore";
    private static final String ADMIN_LAST_NAME = "Aldridge";
    private static final String DOCTOR_NAME = "Nora";
    private static final String DOCTOR_LAST_NAME = "Ramacey";
    private static final String SPECIAL_IDENTIFIER = "5hfFx3uWbB";

    public static CredentialsDto getCredentialsPatientDtoMock() {
        return getCredentials(NAME, LAST_NAME, null);
    }

    public static CredentialsDto getCredentialsDoctorDtoMock() {
        return getCredentials(DOCTOR_NAME, DOCTOR_LAST_NAME, SPECIAL_IDENTIFIER);
    }

    public static CredentialsDto getCredentialsAdminDtoMock() {
        return getCredentials(ADMIN_NAME, ADMIN_LAST_NAME, null);
    }

    private static CredentialsDto getCredentials(final String name, final String lastName, final String specialId) {
        final CredentialsDto credentials = new CredentialsDto();

        credentials.setAlias(ALIAS);
        credentials.setName(name);
        credentials.setLastName(lastName);
        credentials.setPassword(PASSWORD);
        credentials.setSpecialIdentifier(specialId);

        return credentials;
    }
}
