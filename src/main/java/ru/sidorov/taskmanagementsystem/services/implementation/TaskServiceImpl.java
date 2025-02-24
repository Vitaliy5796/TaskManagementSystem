package ru.sidorov.taskmanagementsystem.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sidorov.taskmanagementsystem.mappers.comment.CommentMapper;
import ru.sidorov.taskmanagementsystem.mappers.task.TaskMapper;
import ru.sidorov.taskmanagementsystem.models.dto.comment.CommentDto;
import ru.sidorov.taskmanagementsystem.models.dto.task.TaskDto;
import ru.sidorov.taskmanagementsystem.models.dto.task.TaskUpdateDto;
import ru.sidorov.taskmanagementsystem.models.entities.Task;
import ru.sidorov.taskmanagementsystem.models.entities.User;
import ru.sidorov.taskmanagementsystem.models.enums.Status;
import ru.sidorov.taskmanagementsystem.models.exception.NotFoundTaskException;
import ru.sidorov.taskmanagementsystem.models.exception.NotFoundUserException;
import ru.sidorov.taskmanagementsystem.repositories.CommentRepository;
import ru.sidorov.taskmanagementsystem.repositories.TaskRepository;
import ru.sidorov.taskmanagementsystem.repositories.UserRepository;
import ru.sidorov.taskmanagementsystem.services.abstracts.TaskService;

import java.util.ArrayList;
import java.util.List;

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
        task.setVersion(1);
        Task saveTask = taskRepository.save(task);

        return taskMapper.toTaskDto(saveTask);
    }

    @Override
    @Transactional
    public TaskDto update(TaskUpdateDto taskDto, Integer taskId, User user) {
        checkTask(taskId);

        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundTaskException(taskId));

        taskMapper.updateFromTaskDto(taskDto, task);
        task.setVersion(taskDto.getVersion() + 1);
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
    public TaskDto setStatusTask(String status, Integer taskId, User user) {
        if (!checkAssigneeTask(taskId, user)) {
            throw new IllegalArgumentException("Назначать статус может только исполнитель");
        }

        Task task = taskRepository.findById(taskId).get();

        task.setStatus(Status.valueOf(status));
        taskRepository.save(task);

        return taskMapper.toTaskDto(task);
    }

    @Override
    public Page<TaskDto> getTasks(Integer authorId, Integer assigneeId, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return taskRepository.findByAuthorOrAssignee(authorId, assigneeId, pageable)
                .map(taskMapper::toTaskDto);
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
