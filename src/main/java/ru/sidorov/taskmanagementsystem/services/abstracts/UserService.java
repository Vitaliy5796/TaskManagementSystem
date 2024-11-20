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

    /**
     * Get user from database
     *
     * @param id {@link Integer}
     * @return {@link UserDto}
     */
    UserDto getUserById(Integer id);

    /**
     * Delete user from database
     *
     * @param id {@link Integer}
     */
    void deleteUserById(Integer id);

    /**
     * Update user in database
     *
     * @param userDto {@link UserSaveDto}
     * @return {@link UserDto}
     */
    UserDto updateUser(UserSaveDto userDto);
}
