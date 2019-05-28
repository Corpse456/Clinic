package by.gp.clinic.controller;

import by.gp.clinic.dbo.UserDbo;
import by.gp.clinic.dto.CredentialsDto;
import by.gp.clinic.exception.DoctorNotExistsException;
import by.gp.clinic.exception.PatientNotExistsException;
import by.gp.clinic.exception.UserExistsException;
import by.gp.clinic.facade.UserFacade;
import by.gp.clinic.service.TokenAuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@Api(tags = "User Controller",
    value = "API methods to work with users")
public class UserController {

    private final UserFacade userFacade;
    private final TokenAuthenticationService tokenAuthenticationService;

    @PostMapping(value = "/public/user")
    @ApiOperation(value = "Create a new user")
    public String hireNewDoctor(@RequestBody @Validated CredentialsDto credentials, final HttpServletResponse response)
        throws DoctorNotExistsException, PatientNotExistsException, UserExistsException {
        final UserDbo user = userFacade.createUser(credentials);
        tokenAuthenticationService.addAuthentication(response, user.getAlias(), user.getRole().name());
        return new JSONObject().put("id", user).toString();
    }
}
