package ru.sidorov.taskmanagementsystem.services.abstracts;

import ru.sidorov.taskmanagementsystem.models.dto.task.TaskDto;
import ru.sidorov.taskmanagementsystem.models.entities.User;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    TaskDto save(TaskDto taskDto, User user, Integer assigneeId);

    TaskDto update(TaskDto taskDto, User user);

    List<TaskDto> getAllTasks();

    TaskDto getTaskById(Integer id);

    void deleteTaskById(Integer id);

    TaskDto setNewAssigneeTask(Integer taskId, Integer assigneeId);

    TaskDto setStatusTask(String status, Integer taskId, User user);
}
