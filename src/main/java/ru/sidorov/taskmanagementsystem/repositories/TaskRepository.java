package ru.sidorov.taskmanagementsystem.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sidorov.taskmanagementsystem.models.entities.Task;
import ru.sidorov.taskmanagementsystem.models.entities.User;
import ru.sidorov.taskmanagementsystem.models.enums.Priority;
import ru.sidorov.taskmanagementsystem.models.enums.Status;

@Repository
public interface TaskRepository extends BaseRepository<Task> {

    @Query("SELECT t FROM Task t WHERE (:authorId IS NULL OR t.author.id = :authorId) " +
            "AND (:assigneeId IS NULL OR t.assignee.id = :assigneeId)")
    Page<Task> findByAuthorOrAssignee(@Param("authorId") Integer authorId,
                                      @Param("assigneeId") Integer assigneeId,
                                      Pageable pageable);

//    Page<Task> findByAssignee(User assignee, Pageable pageable);
//
//    Page<Task> findByAuthor(User author, Pageable pageable);
//
//    @Query("SELECT t FROM Task t WHERE t.status = :status AND t.priority = :priority")
//    Page<Task> findByStatusAndPriority(@Param("status") Status status, @Param("priority") Priority priority, Pageable pageable);
}
