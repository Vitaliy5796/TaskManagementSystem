package ru.sidorov.taskmanagementsystem.services.abstracts;

import ru.sidorov.taskmanagementsystem.models.dto.response.JwtResponse;
import ru.sidorov.taskmanagementsystem.models.dto.user.CredentialsDto;
import ru.sidorov.taskmanagementsystem.models.dto.user.UserDto;
import ru.sidorov.taskmanagementsystem.models.dto.user.UserSaveDto;

public interface UserService {

    UserDto save(UserSaveDto userDto);

    UserDto getUserById(Integer id);

    void deleteUserById(Integer id);

    UserDto updateUser(UserSaveDto userDto);

    JwtResponse getToken(CredentialsDto credentialsDto);
}
