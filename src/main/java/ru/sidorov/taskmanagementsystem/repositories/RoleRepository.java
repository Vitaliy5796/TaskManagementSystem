package ru.sidorov.taskmanagementsystem.repositories;

import org.springframework.stereotype.Repository;
import ru.sidorov.taskmanagementsystem.models.entities.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends BaseRepository<Role> {

    Optional<Role> findById(Integer id);
}
