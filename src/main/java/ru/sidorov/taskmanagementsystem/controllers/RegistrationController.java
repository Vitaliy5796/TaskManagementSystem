package ru.sidorov.taskmanagementsystem.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.sidorov.taskmanagementsystem.models.dto.common.TmsResponseEntity;
import ru.sidorov.taskmanagementsystem.models.dto.common.TmsResponseErrorEntity;
import ru.sidorov.taskmanagementsystem.models.dto.common.TmsResponseOkEntity;
import ru.sidorov.taskmanagementsystem.models.dto.response.JwtResponse;
import ru.sidorov.taskmanagementsystem.models.dto.user.CredentialsDto;
import ru.sidorov.taskmanagementsystem.models.dto.user.UserDto;
import ru.sidorov.taskmanagementsystem.models.dto.user.UserSaveDto;
import ru.sidorov.taskmanagementsystem.services.abstracts.UserService;
import ru.sidorov.taskmanagementsystem.services.implementation.AuthenticationService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Api(value = "rest-api")
@Slf4j
public class RegistrationController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @ApiOperation(value = "Регистрация нового пользователя")
    @RequestMapping(value = "/registration", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public TmsResponseEntity<UserDto> registration(@Valid @RequestBody UserSaveDto userDto) {
        log.info("[registration] Starting");
        TmsResponseEntity<UserDto> responseEntity;

        try {
            responseEntity = new TmsResponseOkEntity<>(userService.save(userDto));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TmsResponseErrorEntity<>(e);
        }

        log.info("[registration] Done");
        return responseEntity;
    }

    @ApiOperation(value = "Аутентификация пользователя")
    @RequestMapping(value = "/token", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public TmsResponseEntity<JwtResponse> authUser(@Valid @RequestBody CredentialsDto credentialsDto, HttpServletResponse response) {
        log.info("[authUser] Starting");
        TmsResponseEntity<JwtResponse> responseEntity;

        try {
            responseEntity = new TmsResponseOkEntity<>(authenticationService.authenticateAndGetToken(credentialsDto, response));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TmsResponseErrorEntity<>(e);
        }
        log.info("[authUser] Done");
        return responseEntity;
    }
}
