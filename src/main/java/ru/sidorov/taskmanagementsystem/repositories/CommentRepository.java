package ru.sidorov.taskmanagementsystem.repositories;

import org.springframework.stereotype.Repository;
import ru.sidorov.taskmanagementsystem.models.entities.Comment;

@Repository
public interface CommentRepository extends BaseRepository<Comment> {
}
