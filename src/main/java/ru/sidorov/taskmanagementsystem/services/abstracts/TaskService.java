package ru.sidorov.taskmanagementsystem.services.abstracts;

import org.springframework.data.domain.Page;
import ru.sidorov.taskmanagementsystem.models.dto.comment.CommentDto;
import ru.sidorov.taskmanagementsystem.models.dto.task.TaskDto;
import ru.sidorov.taskmanagementsystem.models.entities.User;

import java.util.List;

public interface TaskService {

    /**
     * Save task in database
     *
     * @param taskDto    {@link TaskDto}
     * @param user       {@link User}
     * @param assigneeId {@link Integer}
     * @return {@link TaskDto}
     */
    TaskDto save(TaskDto taskDto, User user, Integer assigneeId);

    /**
     * Update task in database
     *
     * @param taskDto {@link TaskDto}
     * @param user    {@link User}
     * @return {@link TaskDto}
     */
    TaskDto update(TaskDto taskDto, User user);

    /**
     * Get list tasks from database
     *
     * @return {@link List<TaskDto>}
     */
    List<TaskDto> getAllTasks();

    /**
     * Get task from database
     *
     * @param id {@link Integer}
     * @return {@link TaskDto}
     */
    TaskDto getTaskById(Integer id);

    /**
     * Delete task from database
     *
     * @param id {@link Integer}
     */
    void deleteTaskById(Integer id);

    /**
     * Set new assignee in a task
     *
     * @param taskId {@link Integer}
     * @param assigneeId {@link Integer}
     * @return {@link TaskDto}
     */
    TaskDto setNewAssigneeTask(Integer taskId, Integer assigneeId);

    /**
     * Set status in a task
     *
     * @param status {@link String}
     * @param taskId {@link Integer}
     * @param user {@link User}
     * @return {@link TaskDto}
     */
    TaskDto setStatusTask(String status, Integer taskId, User user);

    /**
     * Get page tasks from database
     *
     * @param authorId {@link Integer}
     * @param assigneeId {@link Integer}
     * @param page {@link Integer}
     * @param size {@link Integer}
     * @return {@link Page<TaskDto>}
     */
    Page<TaskDto> getTasks(Integer authorId, Integer assigneeId, int page, int size);

    /**
     * Get list comments from database
     *
     * @param taskId {@link Integer}
     * @return {@link List<CommentDto>}
     */
    List<CommentDto> getComments(Integer taskId);
}
