package ru.sidorov.taskmanagementsystem.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sidorov.taskmanagementsystem.mappers.user.UserMapper;
import ru.sidorov.taskmanagementsystem.models.dto.response.JwtResponse;
import ru.sidorov.taskmanagementsystem.models.dto.user.CredentialsDto;
import ru.sidorov.taskmanagementsystem.models.dto.user.UserDto;
import ru.sidorov.taskmanagementsystem.models.dto.user.UserSaveDto;
import ru.sidorov.taskmanagementsystem.models.entities.Role;
import ru.sidorov.taskmanagementsystem.models.entities.User;
import ru.sidorov.taskmanagementsystem.models.exception.NotFoundRoleException;
import ru.sidorov.taskmanagementsystem.repositories.RoleRepository;
import ru.sidorov.taskmanagementsystem.repositories.UserRepository;
import ru.sidorov.taskmanagementsystem.services.abstracts.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;


    @Override
    @Transactional
    public UserDto save(UserSaveDto userDto) {
        User user = userMapper.toUser(userDto);
        user.setId(null);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findById(2).orElseThrow(() -> new NotFoundRoleException(2));
        user.setRole(role);
        User saveUser = userRepository.save(user);
        return userMapper.toUserDto(saveUser);
    }

    @Override
    public JwtResponse getToken(CredentialsDto credentialsDto) {
        String login = credentialsDto.getLogin();
        return new JwtResponse(login, "");
    }

    @Override
    public UserDto getUserById(Integer id) {
        return null;
    }

    @Override
    public void deleteUserById(Integer id) {

    }

    @Override
    public UserDto updateUser(UserSaveDto userDto) {
        return null;
    }
}
