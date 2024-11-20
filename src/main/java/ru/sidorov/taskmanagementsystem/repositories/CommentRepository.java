package ru.sidorov.taskmanagementsystem.repositories;

import org.springframework.stereotype.Repository;
import ru.sidorov.taskmanagementsystem.models.entities.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends BaseRepository<Comment> {

    List<Comment> findByTaskId(Integer taskId);
}
