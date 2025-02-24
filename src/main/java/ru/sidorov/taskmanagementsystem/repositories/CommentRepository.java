package ru.sidorov.taskmanagementsystem.repositories;

import org.springframework.stereotype.Repository;
import ru.sidorov.taskmanagementsystem.models.entities.Comment;

import java.util.List;

/**
 * Репозиторий комментариев
 */
@Repository
public interface CommentRepository extends BaseRepository<Comment> {

    /**
     * Находит комментарии по id задачи
     *
     * @param taskId id задачи
     * @return Список комментариев указанной задачи
     */
    List<Comment> findByTaskId(Integer taskId);
}
