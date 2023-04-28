package org.kursovoi.server.controller;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.*;
import org.kursovoi.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<List<UserDto>> getAllUsers() {
        var users = service.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<UserDto> findUserDto(@PathVariable long id) {
        var user = service.findUserDto(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("{id}/operations")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<List<OperationDto>> findAllOperationsOfUser(@PathVariable String id) {
        var operations = service.findAllOperationsOfUser(id);
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }

    @GetMapping("/{id}/loanOrders")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<List<LoanOrderDto>> findLoansOrdersOfUser(@PathVariable String id) {
        var loanOrders = service.findLoansOrdersOfUser(id);
        return new ResponseEntity<>(loanOrders, HttpStatus.OK);
    }

    @GetMapping("/{id}/depositOrders")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<List<DepositOrderDto>> findDepositOrdersOfUser(@PathVariable String id) {
        var depositOrders = service.findDepositOrdersOfUser(id);
        return new ResponseEntity<>(depositOrders, HttpStatus.OK);
    }

    @GetMapping("/{id}/accounts")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<List<AccountDto>> getAccountsOfUser(@PathVariable String id) {
        var accounts = service.getAccountsOfUser(id);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @PostMapping
    @RolesAllowed({"ROLE_USER"})
    public ResponseEntity<?> createUser(@RequestBody CreateUserDto dto) {
        service.createUser(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<?> updateUserStatus(@PathVariable long id, @RequestParam String newStatus) {
        UpdateStatusDto dto = new UpdateStatusDto(newStatus, id);
        service.updateUserStatus(dto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    @RolesAllowed({"ROLE_USER"})
    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody UserDto dto) {
        dto.setId(id);
        service.updateUser(dto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/me")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<UserDto> getMe() {
        var user = service.getMe();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

