package ru.sidorov.taskmanagementsystem.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.domain.Page;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Api(value = "rest-api")
@Slf4j
public class UserController {

    private final TaskService taskService;
    private final CommentService commentService;
    private final JwtUtils jwtUtils;

    /**
     * Установка статуса задачи
     *
     * @param status Статус задачи (PENDING, IN_PROGRESS или COMPLETED)
     * @param taskId id задачи для установки статуса
     * @param request HTTP-запрос, содержащий JWT-токен
     * @return Задачу с обновленным статусом в формате TmsResponseEntity
     */
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

    /**
     * Добавление комментария к задаче
     *
     * @param commentSaveDtoDto Комментарий для задачи
     * @param request HTTP-запрос, содержащий JWT-токен
     * @return Созданный комментарий в формате TmsResponseEntity
     */
    @ApiOperation(value = "Добавление комментария к задачи")
    @RequestMapping(value = "/task/addComment", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public TmsResponseEntity<CommentDto> addComment(@Valid @RequestBody CommentSaveDto commentSaveDtoDto,
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

    /**
     * Получение задач с фильтрацией по автору или исполнителю и пагинацией
     *
     * @param authorId  id автора задачи (может быть {@code null}, тогда фильтр не применяется)
     * @param assigneeId id исполнителя задачи (может быть {@code null}, тогда фильтр не применяется)
     * @param page      номер страницы (начиная с 0)
     * @param size      количество элементов на странице
     * @return Страницу задач в формате TmsResponseEntity
     */
    @ApiOperation("Получение задач автора или исполнителя с фильтрацией и пагинацией")
    @RequestMapping(value = "/tasks", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public TmsResponseEntity<Page<TaskDto>> getTasks(@RequestParam(value = "authorId", required = false) Integer authorId,
                                                     @RequestParam(value = "assigneeId", required = false) Integer assigneeId,
                                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size) {

        log.info("[getTasks] Starting");
        TmsResponseEntity<Page<TaskDto>> responseEntity;

        try {
            responseEntity = new TmsResponseOkEntity<>(taskService.getTasks(authorId, assigneeId, page, size));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TmsResponseErrorEntity<>(e);
        }

        log.info("[getTasks] Done");
        return responseEntity;
    }

    /**
     * Получение комментариев к задачу
     *
     * @param taskId id задачи
     * @return Список комментариев к задаче в формате TmsResponseEntity
     */
    @ApiOperation("Получение комментариев для задачи")
    @RequestMapping(value = "/{taskId}/comments", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public TmsResponseEntity<List<CommentDto>> getComments(@PathVariable Integer taskId) {
        log.info("[getComments] Starting");
        TmsResponseEntity<List<CommentDto>> responseEntity;

        try {
            responseEntity = new TmsResponseOkEntity<>(commentService.getComments(taskId));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TmsResponseErrorEntity<>(e);
        }

        log.info("[getComments] Done");
        return responseEntity;
    }
}
