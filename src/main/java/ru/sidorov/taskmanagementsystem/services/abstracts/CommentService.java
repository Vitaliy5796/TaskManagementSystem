package ru.sidorov.taskmanagementsystem.services.abstracts;

import ru.sidorov.taskmanagementsystem.models.dto.comment.CommentDto;
import ru.sidorov.taskmanagementsystem.models.dto.comment.CommentSaveDto;
import ru.sidorov.taskmanagementsystem.models.entities.User;

import java.util.List;

public interface CommentService {

    /**
     * Save comment in database
     *
     * @param commentSaveDto {@link CommentSaveDto}
     * @param user {@link User}
     * @return {@link CommentDto}
     */
    CommentDto saveComment(CommentSaveDto commentSaveDto, User user);

    /**
     * Get list comments from database
     *
     * @param taskId {@link Integer}
     * @return {@link List <CommentDto>}
     */
    List<CommentDto> getComments(Integer taskId);
}
