package by.gp.clinic.controller;

import by.gp.clinic.dbo.AbstractDbo;
import by.gp.clinic.dto.AbstractDto;
import by.gp.clinic.dto.PageDto;
import by.gp.clinic.dto.UserDto;
import by.gp.clinic.mock.CredentialsMock;
import by.gp.clinic.mock.UserMock;
import by.gp.clinic.repository.CustomRepository;
import by.gp.clinic.repository.UserRepository;
import by.gp.clinic.search.UserSearchRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerTest extends AbstractControllerTest {

    private static final String USER_URL = "/user";
    private static final long EXISTS_ID = 1L;
    private static final long NOT_EXISTS_ID = 10000L;
    private static final String ALIAS = "admin";
    private static final String PASSWORD = "password";

    @Autowired
    private UserRepository repository;

    @Test
    public void createUserPatientTest() {
        addEntity(CredentialsMock.getCredentialsPatientDtoMock(), "/public" + getUrl());
    }

    @Test
    public void createUserPatientTwiceTest() {
        final var credentials = CredentialsMock.getCredentialsPatientDtoMock();
        addEntity(credentials, "/public" + getUrl());
        addEntityWithStatus(credentials, 400, "User Alias already exists", "/public" + getUrl());
    }

    @Test
    public void createUserDoctorTest() {
        addEntity(CredentialsMock.getCredentialsDoctorDtoMock(), "/public" + getUrl());
    }

    @Test
    public void createAdminTest() {
        addEntity(CredentialsMock.getCredentialsAdminDtoMock(), "/admin" + getUrl());
    }

    @Test
    public void getUserTest() {
        getEntityTest(UserDto.class, EXISTS_ID);
    }

    @Test
    public void getUserNotExistsTest() {
        final var result = getQuery(getUrl() + "/" + NOT_EXISTS_ID);
        assertEquals(404, result.getResponse().getStatus());
    }

    @Test
    public void searchUserTest() {
        findEntitiesTest(new UserSearchRequest(), new TypeReference<PageDto<UserDto>>() {
        });
    }

    @Override
    protected CustomRepository<? extends AbstractDbo, Long> getRepository() {
        return repository;
    }

    @Override
    protected AbstractDto getDtoMock() {
        return UserMock.getUserPatientDtoMock();
    }

    @Override
    protected String getUrl() {
        return USER_URL;
    }
}
