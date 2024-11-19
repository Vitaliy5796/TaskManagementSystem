package ru.sidorov.taskmanagementsystem.controllers;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.sidorov.taskmanagementsystem.models.dto.common.TmsResponseEntity;
import ru.sidorov.taskmanagementsystem.models.dto.common.TmsResponseOkEntity;
import ru.sidorov.taskmanagementsystem.services.abstracts.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Api(value = "rest-api")
@Slf4j
public class UserController {

    private final UserService userService;

    @RequestMapping(value = "/", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public TmsResponseEntity<String> test() {
        return new TmsResponseOkEntity<>("User controller test");
    }

//    @ApiOperation(value = "Получение задачи по id")
//    @RequestMapping(value = "/task/{id}", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)




//    public TmsResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {}
}
