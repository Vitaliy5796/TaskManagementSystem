package ru.sidorov.taskmanagementsystem.factories;

import ru.sidorov.taskmanagementsystem.models.dto.comment.CommentDto;
import ru.sidorov.taskmanagementsystem.models.dto.comment.CommentSaveDto;
import ru.sidorov.taskmanagementsystem.models.dto.task.TaskDto;
import ru.sidorov.taskmanagementsystem.models.dto.user.UserDto;
import ru.sidorov.taskmanagementsystem.models.entities.Comment;
import ru.sidorov.taskmanagementsystem.models.entities.Role;
import ru.sidorov.taskmanagementsystem.models.entities.Task;
import ru.sidorov.taskmanagementsystem.models.entities.User;
import ru.sidorov.taskmanagementsystem.models.enums.Priority;
import ru.sidorov.taskmanagementsystem.models.enums.Status;

import java.util.List;

public class TestDataFactory {
    private static final User user = User.builder().id(1).email("user@mail.ru").username("user").role(new Role(1, "ROLE_USER")).build();
    private static final User admin = User.builder().id(2).email("admin@mail.ru").username("admin").role(new Role(1, "ROLE_ADMIN")).build();
    public static final UserDto userDto = UserDto.builder().id(1).email("user@mail.ru").username("user").build();
    public static final UserDto adminDto = UserDto.builder().id(2).email("admin@mail.ru").username("admin").build();


    public static TaskDto createTaskDto() {
        return TaskDto.builder()
                .id(1)
                .title("Заголовок задачи")
                .description("Описание задачи")
                .status("PENDING")
                .priority("HIGH")
                .author(adminDto)
                .assignee(userDto)
                .comments(List.of())
                .build();
    }

    public static Task createTask() {

        return Task.builder()
                .id(1)
                .title("Заголовок задачи")
                .description("Описание задачи")
                .status(Status.PENDING)
                .priority(Priority.HIGH)
                .author(admin)
                .assignee(user)
                .comments(List.of())
                .build();

    }

    public static CommentSaveDto createCommentSaveDto() {

        TaskDto taskDto = createTaskDto();

        return CommentSaveDto.builder()
                .content("content")
                .task(taskDto)
                .build();
    }

    public static CommentDto createCommentDto() {
        return CommentDto.builder()
                .id(1)
                .content("content")
                .author(adminDto)
                .build();
    }

    public static Comment createComment() {
        Task task = createTask();
        return Comment.builder()
                .id(1)
                .content("content")
                .author(admin)
                .task(task)
                .build();
    }
}
