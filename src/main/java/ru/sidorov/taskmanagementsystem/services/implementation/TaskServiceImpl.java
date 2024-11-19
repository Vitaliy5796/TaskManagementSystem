package ru.sidorov.taskmanagementsystem.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sidorov.taskmanagementsystem.mappers.task.TaskMapper;
import ru.sidorov.taskmanagementsystem.models.dto.task.TaskDto;
import ru.sidorov.taskmanagementsystem.models.entities.Task;
import ru.sidorov.taskmanagementsystem.models.entities.User;
import ru.sidorov.taskmanagementsystem.models.exception.NotFoundTaskException;
import ru.sidorov.taskmanagementsystem.models.exception.NotFoundUserException;
import ru.sidorov.taskmanagementsystem.repositories.TaskRepository;
import ru.sidorov.taskmanagementsystem.repositories.UserRepository;
import ru.sidorov.taskmanagementsystem.services.abstracts.TaskService;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    @Override
    @Transactional
    public TaskDto save(TaskDto taskDto, User user, Integer assigneeId) {
        Task task = taskMapper.toTask(taskDto);
        task.setId(null);

        if (assigneeId != null) {
            task.setAssignee(userRepository.findById(assigneeId).orElseThrow(NotFoundUserException::new));
        }

        if (user != null) {
            task.setAuthor(user);
        }

        Task saveTask = taskRepository.save(task);

        return taskMapper.toTaskDto(saveTask);
    }

    @Override
    @Transactional
    public TaskDto update(TaskDto taskDto, User user) {
        Integer taskId = taskDto.getId();
        if (taskId == null || !taskRepository.existsById(taskId)) {
            throw new IllegalArgumentException("Task id can't be null");
        }

        Task task = taskRepository.findById(taskDto.getId()).orElseThrow(() -> new NotFoundTaskException(taskId));

        taskMapper.updateFromTaskDto(taskDto, task);

        Task saveTask = taskRepository.save(task);

        return taskMapper.toTaskDto(saveTask);
    }
}
