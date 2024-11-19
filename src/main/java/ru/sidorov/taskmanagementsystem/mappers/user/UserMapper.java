package ru.sidorov.taskmanagementsystem.mappers.user;

import org.mapstruct.Mapper;
import ru.sidorov.taskmanagementsystem.models.dto.user.UserDto;
import ru.sidorov.taskmanagementsystem.models.dto.user.UserSaveDto;
import ru.sidorov.taskmanagementsystem.models.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {


    /**
     * map {@link User} to {@link UserDto}
     *
     * @param user {@link User} object
     * @return {@link UserDto}
     */
    UserDto toUserDto(User user);

    /**
     * map {@link UserSaveDto} to {@link User}
     *
     * @param userSaveDto {@link UserSaveDto} object
     * @return {@link User}
     */
    User toUser(UserSaveDto userSaveDto);
}
