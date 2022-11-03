package org.kursovoi.server.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.AuthRequestDto;
import org.kursovoi.server.dto.CreateUserDto;
import org.kursovoi.server.dto.UpdateStatusUserDto;
import org.kursovoi.server.dto.UserDto;
import org.kursovoi.server.model.Role;
import org.kursovoi.server.model.User;
import org.kursovoi.server.model.constant.Status;
import org.kursovoi.server.repository.UserRepository;
import org.kursovoi.server.util.exception.IncorrectPasswordException;
import org.kursovoi.server.util.exception.IncorrectStatusException;
import org.kursovoi.server.util.exception.UserAlreadyExistsException;
import org.kursovoi.server.util.exception.UserNotFoundException;
import org.kursovoi.server.util.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Transactional
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional
    public UserDto findUserDto(long id) {
        return mapper.map(getUser(id));
    }

    @Transactional
    public void createUser(CreateUserDto userDto) {
        if (userRepository.findOptionalByLogin(userDto.getLogin()).isPresent()) {
            throw new UserAlreadyExistsException("User with login: " + userDto.getLogin() + " - already exist");
        }
        if (!userDto.getPassword().equals(userDto.getRepeatPassword())) {
            throw new IncorrectPasswordException("Passwords didn't match");
        }
        var user = mapper.map(userDto);
        user.setStatus(Status.ACTIVE);
        user.setRole(new Role(1L, "user"));
        userRepository.save(user);
    }

    @Transactional
    public void updateUserStatus(UpdateStatusUserDto newStatus) {
        if (Arrays.stream(Status.values()).map(Enum::name).noneMatch(status -> status.equals(newStatus.getNewStatus()))) {
            throw new IncorrectStatusException("Status is incorrect");
        }
        var user = getUser(newStatus.getUserId());
        user.setStatus(Status.valueOf(newStatus.getNewStatus()));
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public UserDto authenticate(AuthRequestDto request) {
        User user = userRepository.findOptionalByLogin(request.getLogin())
                .orElseThrow(() -> new UserNotFoundException("User with login: " + request.getLogin() + " - not found!"));
        if (!user.getPassword().equals(request.getPassword())) {
            throw new IncorrectPasswordException("Password is incorrect");
        }
        return mapper.map(user);
    }

    @Transactional
    User getUser(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " - not found!"));
    }

}
