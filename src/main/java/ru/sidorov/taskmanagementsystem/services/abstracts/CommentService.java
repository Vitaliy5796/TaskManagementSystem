package ru.sidorov.taskmanagementsystem.services.abstracts;

import ru.sidorov.taskmanagementsystem.models.dto.comment.CommentDto;
import ru.sidorov.taskmanagementsystem.models.dto.comment.CommentSaveDto;
import ru.sidorov.taskmanagementsystem.models.entities.User;

import java.util.List;

/**
 * Сервис для работы с комментариями
 */
public interface CommentService {

    /**
     * Сохранение комментария
     *
     * @param commentSaveDto {@link CommentSaveDto} Сохраняемы комментарий
     * @param user {@link User} Пользователь комментария
     * @return {@link CommentDto} Сохраненный комментарий
     */
    CommentDto saveComment(CommentSaveDto commentSaveDto, User user);

    /**
     * Получение списка комментариев задачи
     *
     * @param taskId {@link Integer} id задачи
     * @return {@link List <CommentDto>} Список комментариев задачи
     */
    List<CommentDto> getComments(Integer taskId);
}
