package ru.sidorov.taskmanagementsystem.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sidorov.taskmanagementsystem.models.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

    @Query("SELECT u FROM User u JOIN FETCH u.role where u.email = (:email)")
    Optional<User> getByEmail(String email);

    boolean existsByEmail(String email);
}
