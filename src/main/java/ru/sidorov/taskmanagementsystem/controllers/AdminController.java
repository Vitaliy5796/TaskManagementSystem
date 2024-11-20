package ru.sidorov.taskmanagementsystem.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.*;
import ru.sidorov.taskmanagementsystem.config.jwt.JwtUtils;
import ru.sidorov.taskmanagementsystem.models.dto.common.TmsResponseEntity;
import ru.sidorov.taskmanagementsystem.models.dto.common.TmsResponseErrorEntity;
import ru.sidorov.taskmanagementsystem.models.dto.common.TmsResponseOkEntity;
import ru.sidorov.taskmanagementsystem.models.dto.task.TaskDto;
import ru.sidorov.taskmanagementsystem.models.dto.user.UserDto;
import ru.sidorov.taskmanagementsystem.models.dto.user.UserSaveDto;
import ru.sidorov.taskmanagementsystem.models.entities.User;
import ru.sidorov.taskmanagementsystem.services.abstracts.TaskService;
import ru.sidorov.taskmanagementsystem.services.abstracts.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Api(value = "rest-api")
@Slf4j
public class AdminController {

    private final UserService userService;
    private final TaskService taskService;
    private final JwtUtils jwtUtils;

    @ApiOperation(value = "Создание задачи")
    @RequestMapping(value = "/task", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public TmsResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto,
                                                 @RequestParam("assigneeId") Integer assigneeId,
                                                 HttpServletRequest request) {
        log.info("[createTask] Starting");
        TmsResponseEntity<TaskDto> responseEntity;

        try {
            responseEntity = new TmsResponseOkEntity<>(taskService.save(taskDto,
                    jwtUtils.getUserFromToken(jwtUtils.getTokenFromRequest(request)),
                    assigneeId
            ));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TmsResponseErrorEntity<>(e);
        }


        log.info("[createTask] Done");
        return responseEntity;
    }

    @ApiOperation(value = "Обновление задачи")
    @RequestMapping(value = "/task", produces = "application/json;charset=UTF-8", method = RequestMethod.PATCH)
    public TmsResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto, HttpServletRequest request) {
        log.info("[updateTask] Starting");
        TmsResponseEntity<TaskDto> responseEntity;

        try {
            User currentUser = jwtUtils.getUserFromToken(jwtUtils.getTokenFromRequest(request));
            responseEntity = new TmsResponseOkEntity<>(taskService.update(taskDto, currentUser));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TmsResponseErrorEntity<>(e);
        }

        log.info("[updateTask] Done");
        return responseEntity;
    }

    @ApiOperation(value = "Получение всех задач")
    @RequestMapping(value = "/tasks", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public TmsResponseEntity<List<TaskDto>> getAllTasks() {
        log.info("[getAllTasks] Starting");
        TmsResponseEntity<List<TaskDto>> responseEntity;

        try {
            responseEntity = new TmsResponseOkEntity<>(taskService.getAllTasks());
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TmsResponseErrorEntity<>(e);
        }

        log.info("[getAllTasks] Done");
        return responseEntity;
    }

    @ApiOperation(value = "Получение задачи по taskId")
    @RequestMapping(value = "/task/{taskId}", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public TmsResponseEntity<TaskDto> getTaskById(@PathVariable Integer taskId) {
        log.info("[getTaskById] Starting");
        TmsResponseEntity<TaskDto> responseEntity;

        try {
            responseEntity = new TmsResponseOkEntity<>(taskService.getTaskById(taskId));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TmsResponseErrorEntity<>(e);
        }

        log.info("[getTaskById] Done");
        return responseEntity;
    }

    @ApiOperation(value = "Назначение нового исполнителя по taskId и userId")
    @RequestMapping(value = "/task/{taskId}", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public TmsResponseEntity<TaskDto> setTaskNewAssignee(@PathVariable Integer taskId,
                                                        @RequestParam Integer userId) {
        log.info("[setTaskNewAssignee] Starting");
        TmsResponseEntity<TaskDto> responseEntity;

        try {
            responseEntity = new TmsResponseOkEntity<>(taskService.setNewAssigneeTask(taskId, userId));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TmsResponseErrorEntity<>(e);
        }

        log.info("[setTaskNewAssignee] Done");
        return responseEntity;
    }

    @ApiOperation(value = "Удаление задачи по taskId")
    @RequestMapping(value = "/task/{taskId}", produces = "application/json;charset=UTF-8", method = RequestMethod.DELETE)
    public TmsResponseEntity<String> delTaskById(@PathVariable Integer taskId) {
        log.info("[delTaskById] Starting");
        TmsResponseEntity<String> responseEntity;

        try {
            taskService.deleteTaskById(taskId);
            responseEntity = new TmsResponseOkEntity<>("Задача удалена");
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TmsResponseErrorEntity<>(e);
        }

        log.info("[delTaskById] Done");
        return responseEntity;
    }
}
