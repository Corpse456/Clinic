package by.gp.clinic.controller;

import by.gp.clinic.dbo.AbstractDbo;
import by.gp.clinic.dto.AbstractDto;
import by.gp.clinic.dto.CredentialsDto;
import by.gp.clinic.dto.PageDto;
import by.gp.clinic.dto.UserDto;
import by.gp.clinic.mock.CredentialsMock;
import by.gp.clinic.mock.UserMock;
import by.gp.clinic.repository.CustomRepository;
import by.gp.clinic.repository.UserRepository;
import by.gp.clinic.search.UserSearchRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserControllerTest extends AbstractControllerTest {

    private static final String USER_URL = "/user";

    @Autowired
    private UserRepository repository;

    @Test
    public void createUserPatientTest() {
        addEntity(CredentialsMock.getCredentialsPatientDtoMock(), "/public" + getUrl());
    }

    @Test
    public void createUserPatientTwiceTest() {
        final CredentialsDto credentials = CredentialsMock.getCredentialsPatientDtoMock();
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
        getEntityTest(UserDto.class, 1L);
    }

    @Test
    public void getUserNotExistsTest() {
        getEntityTest(UserDto.class, 10000L);
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