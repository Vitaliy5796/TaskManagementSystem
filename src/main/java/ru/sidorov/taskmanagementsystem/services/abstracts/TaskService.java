package ru.sidorov.taskmanagementsystem.services.abstracts;

import ru.sidorov.taskmanagementsystem.models.dto.task.TaskDto;
import ru.sidorov.taskmanagementsystem.models.entities.User;

public interface TaskService {

    TaskDto save(TaskDto taskDto, User user, Integer assigneeId);
}
