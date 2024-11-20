package ru.sidorov.taskmanagementsystem.mappers.comment;

import org.mapstruct.Mapper;
import ru.sidorov.taskmanagementsystem.mappers.task.TaskMapper;
import ru.sidorov.taskmanagementsystem.mappers.user.UserMapper;
import ru.sidorov.taskmanagementsystem.models.dto.comment.CommentDto;
import ru.sidorov.taskmanagementsystem.models.dto.comment.CommentSaveDto;
import ru.sidorov.taskmanagementsystem.models.entities.Comment;

@Mapper(componentModel = "spring", uses = {UserMapper.class, TaskMapper.class})
public interface CommentMapper {


    /**
     * map {@link Comment} to {@link CommentDto}
     *
     * @param comment {@link Comment} object
     * @return {@link CommentDto}
     */
    CommentDto toCommentDto(Comment comment);

    /**
     * map {@link CommentSaveDto} to {@link Comment}
     *
     * @param commentSaveDto {@link CommentSaveDto} object
     * @return {@link Comment}
     */
    Comment toComment(CommentSaveDto commentSaveDto);
}
