package ru.sidorov.taskmanagementsystem.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sidorov.taskmanagementsystem.mappers.task.TaskMapper;
import ru.sidorov.taskmanagementsystem.models.dto.task.TaskDto;
import ru.sidorov.taskmanagementsystem.models.entities.Task;
import ru.sidorov.taskmanagementsystem.models.entities.User;
import ru.sidorov.taskmanagementsystem.models.enums.Status;
import ru.sidorov.taskmanagementsystem.models.exception.NotFoundTaskException;
import ru.sidorov.taskmanagementsystem.models.exception.NotFoundUserException;
import ru.sidorov.taskmanagementsystem.repositories.TaskRepository;
import ru.sidorov.taskmanagementsystem.repositories.UserRepository;
import ru.sidorov.taskmanagementsystem.services.abstracts.TaskService;

import java.util.List;
import java.util.Optional;

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

        checkTask(taskId);

        Task task = taskRepository.findById(taskDto.getId()).orElseThrow(() -> new NotFoundTaskException(taskId));

        taskMapper.updateFromTaskDto(taskDto, task);

        Task saveTask = taskRepository.save(task);

        return taskMapper.toTaskDto(saveTask);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toTaskDto)
                .toList();
    }

    @Override
    public TaskDto getTaskById(Integer id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(NotFoundTaskException::new);
        return taskMapper.toTaskDto(task);
    }

    @Override
    @Transactional
    public void deleteTaskById(Integer id) {
        checkTask(id);

        taskRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TaskDto setNewAssigneeTask(Integer taskId, Integer assigneeId) {
        checkTask(taskId);

        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundTaskException(taskId));
        task.setAssignee(userRepository.findById(assigneeId).orElseThrow(NotFoundUserException::new));
        taskRepository.save(task);

        return taskMapper.toTaskDto(task);
    }

    @Override
    @Transactional
    public TaskDto setStatusTask(String status, Integer taskId, User user) {
        if (!checkAssigneeTask(taskId, user)) {
            throw new IllegalArgumentException("Назначать статус может только исполнитель");
        }

        Task task = taskRepository.findById(taskId).get();

        task.setStatus(Status.valueOf(status));
        taskRepository.save(task);

        return taskMapper.toTaskDto(task);
    }

    private boolean checkAssigneeTask(Integer taskId, User user) {
        return taskRepository.findById(taskId).orElseThrow(NotFoundTaskException::new).getAssignee().equals(user);
    }

    private void checkTask(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Task id can't be null");
        }
        if (!taskRepository.existsById(id)) {
            throw new NotFoundTaskException(id);
        }
    }
}
