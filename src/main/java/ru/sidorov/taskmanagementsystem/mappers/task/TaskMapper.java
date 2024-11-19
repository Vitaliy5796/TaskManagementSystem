package ru.sidorov.taskmanagementsystem.mappers.task;

import org.mapstruct.Mapper;
import ru.sidorov.taskmanagementsystem.mappers.comment.CommentMapper;
import ru.sidorov.taskmanagementsystem.mappers.user.UserMapper;
import ru.sidorov.taskmanagementsystem.models.dto.task.TaskDto;
import ru.sidorov.taskmanagementsystem.models.entities.Task;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CommentMapper.class})
public interface TaskMapper {

    /**
     * map {@link TaskDto} to {@link Task}
     *
     * @param task {@link Task} object
     * @return {@link TaskDto}
     */
    TaskDto toTaskDto(Task task);

    /**
     * map {@link TaskDto} to {@link Task}
     *
     * @param taskDto {@link TaskDto} object
     * @return {@link Task}
     */
    Task toTask(TaskDto taskDto);
}
