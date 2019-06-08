package by.gp.clinic.controller;

import by.gp.clinic.dbo.UserDbo;
import by.gp.clinic.dto.CredentialsDto;
import by.gp.clinic.dto.PageDto;
import by.gp.clinic.dto.UserDto;
import by.gp.clinic.exception.DoctorNotExistsException;
import by.gp.clinic.exception.PatientNotExistsException;
import by.gp.clinic.exception.UserExistsException;
import by.gp.clinic.exception.UserNotExistsException;
import by.gp.clinic.facade.UserFacade;
import by.gp.clinic.search.UserSearchRequest;
import by.gp.clinic.service.TokenAuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static by.gp.clinic.service.TokenAuthenticationService.HEADER_STRING;

@RestController
@RequiredArgsConstructor
@Api(tags = "User Controller",
    value = "API methods to work with users")
public class UserController {

    private final UserFacade userFacade;
    private final TokenAuthenticationService tokenAuthenticationService;

    @PostMapping(value = "/public/user")
    @ApiOperation(value = "Create a new user")
    public String createUser(@RequestBody @Validated CredentialsDto credentials, final HttpServletResponse response)
        throws DoctorNotExistsException, PatientNotExistsException, UserExistsException {
        final UserDbo user = userFacade.createUser(credentials);
        tokenAuthenticationService.addAuthentication(response, user.getAlias(), user.getRole().name());
        return new JSONObject().put("id", user.getId()).toString();
    }

    @PostMapping(value = "/admin/user")
    @ApiOperation(value = "Create a new admin")
    public String createAdmin(@RequestBody @Validated CredentialsDto credentials) throws UserExistsException {
        final UserDbo user = userFacade.createAdmin(credentials);
        return new JSONObject().put("id", user.getId()).toString();
    }

    @GetMapping(value = "/user/{id}")
    @ApiOperation(value = "Create a new user")
    public UserDto getUser(@PathVariable("id") final Long id) throws UserNotExistsException {
        return userFacade.getUser(id);
    }

    @PostMapping(value = "/user/search")
    @ApiOperation(value = "Search users")
    public PageDto<UserDto> searchUser(@RequestBody final UserSearchRequest searchRequest) {
        return userFacade.searchUsers(searchRequest);
    }

    @PostMapping(value = "/user/logout")
    @ApiOperation(value = "Search users")
    public void logoutUser(@RequestHeader(HEADER_STRING) final String token) {
        userFacade.logout(token);
    }
}
