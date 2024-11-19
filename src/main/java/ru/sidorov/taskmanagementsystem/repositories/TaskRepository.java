package ru.sidorov.taskmanagementsystem.repositories;

import org.springframework.stereotype.Repository;
import ru.sidorov.taskmanagementsystem.models.entities.Task;

@Repository
public interface TaskRepository extends BaseRepository<Task> {
}
