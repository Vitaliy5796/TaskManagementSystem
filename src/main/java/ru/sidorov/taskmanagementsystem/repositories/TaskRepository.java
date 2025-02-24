package ru.sidorov.taskmanagementsystem.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sidorov.taskmanagementsystem.models.entities.Task;

/**
 * Репозиторий задач
 */
@Repository
public interface TaskRepository extends BaseRepository<Task> {

    /**
     * Поиск задач по автору или исполнителю
     *
     * @param authorId  ID автора задачи (может быть {@code null}, тогда фильтр не применяется)
     * @param assigneeId ID исполнителя задачи (может быть {@code null}, тогда фильтр не применяется)
     * @param pageable  объект для пагинации и сортировки результатов
     * @return страница с задачами, удовлетворяющими условиям фильтрации
     */
    @Query("SELECT t FROM Task t WHERE (:authorId IS NULL OR t.author.id = :authorId) " +
            "AND (:assigneeId IS NULL OR t.assignee.id = :assigneeId)")
    Page<Task> findByAuthorOrAssignee(@Param("authorId") Integer authorId,
                                      @Param("assigneeId") Integer assigneeId,
                                      Pageable pageable);
}
