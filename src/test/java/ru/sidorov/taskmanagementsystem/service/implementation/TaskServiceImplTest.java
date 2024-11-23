package ru.sidorov.taskmanagementsystem.service.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sidorov.taskmanagementsystem.factories.TestDataFactory;
import ru.sidorov.taskmanagementsystem.mappers.task.TaskMapper;
import ru.sidorov.taskmanagementsystem.models.dto.task.TaskDto;
import ru.sidorov.taskmanagementsystem.models.entities.Task;
import ru.sidorov.taskmanagementsystem.models.entities.User;
import ru.sidorov.taskmanagementsystem.models.exception.NotFoundTaskException;
import ru.sidorov.taskmanagementsystem.repositories.TaskRepository;
import ru.sidorov.taskmanagementsystem.repositories.UserRepository;
import ru.sidorov.taskmanagementsystem.services.implementation.TaskServiceImpl;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskMapper taskMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TaskRepository taskRepository;

    private TaskDto taskDto;
    private Task task;
    private TaskDto savedTaskDto;

    @BeforeEach
    void setUp() {
        taskDto = TestDataFactory.createTaskDto();
        task = TestDataFactory.createTask();
        savedTaskDto = TestDataFactory.createTaskDto();
    }

    @Test
    void testSave() {
        User assignee = task.getAssignee();

        Mockito.when(taskMapper.toTask(taskDto)).thenReturn(task);
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(assignee));
        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(task);
        Mockito.when(taskMapper.toTaskDto(Mockito.any(Task.class))).thenReturn(savedTaskDto);

        TaskDto saveDto = taskService.save(taskDto, assignee, assignee.getId());
        Assertions.assertEquals(taskDto, saveDto);
    }

    @Test
    void testGetTaskById() {

        Mockito.when(taskRepository.findById(1)).thenReturn(Optional.of(task));
        Mockito.when(taskMapper.toTaskDto(task)).thenReturn(taskDto);

        TaskDto taskById = taskService.getTaskById(1);
        Assertions.assertEquals(taskDto, taskById);
    }

    @Test
    void testDeleteTaskByIdNullId() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> taskService.deleteTaskById(null));

        Assertions.assertEquals("Task id can't be null", exception.getMessage());

        Mockito.verify(taskRepository, Mockito.never()).deleteById(Mockito.any());
    }

    @Test
    void testDeleteTaskByIdNotFound() {
        Mockito.when(taskRepository.existsById(30)).thenReturn(false);

        NotFoundTaskException exception = Assertions.assertThrows(NotFoundTaskException.class, () -> taskService.deleteTaskById(30));
        Assertions.assertEquals("Задача с id = 30 не найдена", exception.getMessage());

        Mockito.verify(taskRepository, Mockito.never()).deleteById(Mockito.any());
    }

    @Test
    void testDeleteTaskById() {
        Mockito.when(taskRepository.existsById(1)).thenReturn(true);

        taskService.deleteTaskById(1);

        Mockito.verify(taskRepository, Mockito.times(1)).deleteById(1);
    }
}
