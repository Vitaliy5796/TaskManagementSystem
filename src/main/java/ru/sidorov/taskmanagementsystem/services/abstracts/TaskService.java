package ru.sidorov.taskmanagementsystem.services.abstracts;

import org.springframework.data.domain.Page;
import ru.sidorov.taskmanagementsystem.models.dto.task.TaskDto;
import ru.sidorov.taskmanagementsystem.models.dto.task.TaskUpdateDto;
import ru.sidorov.taskmanagementsystem.models.entities.User;

import java.util.List;

/**
 * Сервис для работы с задачами
 */
public interface TaskService {

    /**
     * Сохранение задачи
     *
     * @param taskDto    {@link TaskDto} Сохраняемая задача
     * @param user       {@link User} Пользователь создавший задачу
     * @param assigneeId {@link Integer} Исполнитель задачи
     * @return {@link TaskDto} Сохроаненная задача
     */
    TaskDto save(TaskDto taskDto, User user, Integer assigneeId);

    /**
     * Обновление задачи
     *
     * @param taskDto {@link TaskUpdateDto} Обновленные данные задачи
     * @param taskId {@link Integer} id обновляемой задачи
     * @param user    {@link User} Пользователь обновляющий задачу
     * @return {@link TaskDto} Обновленная задача
     */
    TaskDto update(TaskUpdateDto taskDto, Integer taskId, User user);

    /**
     * Получение всех задач
     *
     * @return {@link List<TaskDto>} Список задач
     */
    List<TaskDto> getAllTasks();

    /**
     * Получение задачи по id
     *
     * @param id {@link Integer} id получаемой задачи
     * @return {@link TaskDto} Полученная задача
     */
    TaskDto getTaskById(Integer id);

    /**
     * Удаление задачи по id
     *
     * @param id {@link Integer} id удаляемой задачи
     */
    void deleteTaskById(Integer id);

    /**
     * Установка нового статуса задачи
     *
     * @param status {@link String} Статус задачи (PENDING, IN_PROGRESS или COMPLETED)
     * @param taskId {@link Integer} id задачи для установки статуса
     * @param user {@link User} Пользователь устанавливающий статус
     * @return {@link TaskDto} Задача с обновленным статусом
     */
    TaskDto setStatusTask(String status, Integer taskId, User user);

    /**
     * Поиск задач по автору или исполнителю.
     *
     * @param authorId  id автора задачи (может быть {@code null}, тогда фильтр не применяется)
     * @param assigneeId id исполнителя задачи (может быть {@code null}, тогда фильтр не применяется)
     * @param page      номер страницы (начиная с 0)
     * @param size      количество элементов на странице
     * @return {@link Page<TaskDto>} страница с задачами, удовлетворяющими условиям фильтрации
     */
    Page<TaskDto> getTasks(Integer authorId, Integer assigneeId, int page, int size);
}
