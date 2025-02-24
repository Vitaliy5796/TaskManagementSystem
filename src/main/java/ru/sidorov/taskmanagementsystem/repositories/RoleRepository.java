package ru.sidorov.taskmanagementsystem.repositories;

import org.springframework.stereotype.Repository;
import ru.sidorov.taskmanagementsystem.models.entities.Role;
import ru.sidorov.taskmanagementsystem.models.entities.User;

import java.util.Optional;

/**
 * Репозиторий ролей
 */
@Repository
public interface RoleRepository extends BaseRepository<Role> {

    /**
     * Получение роль по id
     *
     * @param id id роли
     * @return {@link Optional< User >}, содержащий роль, если найдена, иначе пустой Optional
     */
    Optional<Role> findById(Integer id);
}
