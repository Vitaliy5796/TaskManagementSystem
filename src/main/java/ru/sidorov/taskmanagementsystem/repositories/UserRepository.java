package ru.sidorov.taskmanagementsystem.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sidorov.taskmanagementsystem.models.entities.User;

import java.util.Optional;

/**
 * Репозиторий пользователей
 */
@Repository
public interface UserRepository extends BaseRepository<User> {

    /**
     * Поиск пользователя по email с подгрузкой роли.
     *
     * @param email email пользователя
     * @return {@link Optional<User>} содержащий пользователя, иначе пустой Optional
     */
    @Query("SELECT u FROM User u JOIN FETCH u.role where u.email = (:email)")
    Optional<User> getByEmail(String email);
}
