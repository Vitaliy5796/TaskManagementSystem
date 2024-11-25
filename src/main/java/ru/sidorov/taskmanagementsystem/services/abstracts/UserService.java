package ru.sidorov.taskmanagementsystem.services.abstracts;

import ru.sidorov.taskmanagementsystem.models.dto.response.JwtResponse;
import ru.sidorov.taskmanagementsystem.models.dto.user.CredentialsDto;
import ru.sidorov.taskmanagementsystem.models.dto.user.UserDto;
import ru.sidorov.taskmanagementsystem.models.dto.user.UserSaveDto;

public interface UserService {

    /**
     * Save user in database
     *
     * @param userDto {@link UserSaveDto}
     * @return {@link UserDto}
     */
    UserDto save(UserSaveDto userDto);
}
