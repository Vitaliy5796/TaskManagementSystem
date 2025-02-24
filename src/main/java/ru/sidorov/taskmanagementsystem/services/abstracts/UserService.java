package ru.sidorov.taskmanagementsystem.services.abstracts;

import ru.sidorov.taskmanagementsystem.models.dto.user.UserDto;
import ru.sidorov.taskmanagementsystem.models.dto.user.UserSaveDto;

/**
 * Сервис для работы с пользователями
 */
public interface UserService {

    /**
     * Сохранение пользователя
     *
     * @param userDto {@link UserSaveDto} Сохраняемы пользователь
     * @return {@link UserDto} Сохраненный пользователь
     */
    UserDto save(UserSaveDto userDto);
}
