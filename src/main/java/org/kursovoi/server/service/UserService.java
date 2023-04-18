package org.kursovoi.server.service;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.*;
import org.kursovoi.server.model.Role;
import org.kursovoi.server.model.User;
import org.kursovoi.server.model.constant.Status;
import org.kursovoi.server.repository.UserRepository;
import org.kursovoi.server.util.exception.IncorrectPasswordException;
import org.kursovoi.server.util.exception.IncorrectStatusException;
import org.kursovoi.server.util.exception.ModelNotFoundException;
import org.kursovoi.server.util.exception.UserAlreadyExistsException;
import org.kursovoi.server.util.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final OperationService operationService;

    private final LoanOrderService loanOrderService;

    private final DepositOrderService depositOrderService;

    private final AccountService accountService;

    @PostConstruct
    public void init() {
        loanOrderService.setUserService(this);
        depositOrderService.setUserService(this);
        accountService.setUserService(this);
    }

    @Transactional
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional
    public UserDto findUserDto(long id) {
        return mapper.map(getUser(id));
    }

    public List<OperationDto> findAllOperationsOfUser(long id) {
        return operationService.findAllOperationsOfUser(getUser(id));
    }

    public List<LoanOrderDto> findLoansOrdersOfUser(long id) {
        return loanOrderService.findLoansOrdersOfUser(getUser(id));
    }

    public List<DepositOrderDto> findDepositOrdersOfUser(long id) {
        return depositOrderService.findDepositOrdersOfUser(getUser(id));
    }

    public List<AccountDto> getAccountsOfUser(long id) {
        return accountService.getAccountsOfUser(getUser(id));
    }

    @Transactional
    public void createUser(CreateUserDto userDto) {
        if (userRepository.findByLogin(userDto.getLogin()).isPresent()) {
            throw new UserAlreadyExistsException("User with login: " + userDto.getLogin() + " - already exist");
        }
        if (!userDto.getPassword().equals(userDto.getRepeatPassword())) {
            throw new IncorrectPasswordException("Passwords didn't match");
        }
        var user = mapper.map(userDto);
        user.setStatus(Status.ACTIVE);
        user.setRole(new Role(2L, "user"));
        userRepository.save(user);
    }

    @Transactional
    public void updateUserStatus(UpdateStatusDto newStatus) {
        if (Arrays.stream(Status.values()).map(Enum::name).noneMatch(status -> status.equals(newStatus.getNewStatus()))) {
            throw new IncorrectStatusException("Status is incorrect");
        }
        var user = getUser(newStatus.getId());
        user.setStatus(Status.valueOf(newStatus.getNewStatus()));
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(long id) {
        userRepository.delete(getUser(id));
    }

    @Transactional
    public UserDto authenticate(AuthRequestDto request) {
        User user = userRepository.findByLogin(request.getLogin())
                .orElseThrow(() -> new ModelNotFoundException("User with login: " + request.getLogin() + " - not found!"));
        if (!user.getPassword().equals(request.getPassword())) {
            throw new IncorrectPasswordException("Password is incorrect");
        }
        if (user.getStatus().equals(Status.ACTIVE)) {
            return mapper.map(user);
        } else {
            throw new IncorrectPasswordException("User is not active");
        }
    }

    @Transactional
    public void updateUser(UserDto dto) {
        User user = getUser(dto.getId());
        user.setLogin(dto.getLogin());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPhoneNumber(dto.getPhoneNumber());
        userRepository.saveAndFlush(user);

    }

    @Transactional
    User getUser(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("User with id: " + id + " - not found!"));
    }
}
