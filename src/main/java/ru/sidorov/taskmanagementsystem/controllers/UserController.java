package ru.sidorov.taskmanagementsystem.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.sidorov.taskmanagementsystem.config.jwt.JwtUtils;
import ru.sidorov.taskmanagementsystem.models.dto.comment.CommentDto;
import ru.sidorov.taskmanagementsystem.models.dto.comment.CommentSaveDto;
import ru.sidorov.taskmanagementsystem.models.dto.common.TmsResponseEntity;
import ru.sidorov.taskmanagementsystem.models.dto.common.TmsResponseErrorEntity;
import ru.sidorov.taskmanagementsystem.models.dto.common.TmsResponseOkEntity;
import ru.sidorov.taskmanagementsystem.models.dto.task.TaskDto;
import ru.sidorov.taskmanagementsystem.services.abstracts.CommentService;
import ru.sidorov.taskmanagementsystem.services.abstracts.TaskService;
import ru.sidorov.taskmanagementsystem.services.abstracts.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Api(value = "rest-api")
@Slf4j
public class UserController {

    private final UserService userService;
    private final TaskService taskService;
    private final CommentService commentService;
    private final JwtUtils jwtUtils;

    @ApiOperation(value = "Установка статуса задачи по taskId")
    @RequestMapping(value = "/task/{taskId}", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public TmsResponseEntity<TaskDto> setStatusTask(@RequestParam String status,
                                                    @PathVariable Integer taskId,
                                                    HttpServletRequest request) {
        log.info("[setStatusTask] Starting");
        TmsResponseEntity<TaskDto> responseEntity;

        try {
            responseEntity = new TmsResponseOkEntity<>(taskService.setStatusTask(status,
                    taskId,
                    jwtUtils.getUserFromToken(jwtUtils.getTokenFromRequest(request))
            ));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TmsResponseErrorEntity<>(e);
        }

        log.info("[setStatusTask] Done");
        return responseEntity;
    }

    @ApiOperation(value = "Добавление комментария к задачи")
    @RequestMapping(value = "/task/addComment", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public TmsResponseEntity<CommentDto> addComment(@RequestBody CommentSaveDto commentSaveDtoDto,
                                                    HttpServletRequest request) {
        log.info("[addComment] Starting");
        TmsResponseEntity<CommentDto> responseEntity;

        try {
            responseEntity = new TmsResponseOkEntity<>(commentService.saveComment(commentSaveDtoDto,
                    jwtUtils.getUserFromToken(jwtUtils.getTokenFromRequest(request))
            ));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TmsResponseErrorEntity<>(e);
        }

        log.info("[addComment] Done");
        return responseEntity;
    }
}
