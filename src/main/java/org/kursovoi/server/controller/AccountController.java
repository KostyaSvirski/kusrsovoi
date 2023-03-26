package org.kursovoi.server.controller;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.AccountDto;
import org.kursovoi.server.dto.TransactionDto;
import org.kursovoi.server.dto.UpdateStatusDto;
import org.kursovoi.server.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        var accounts = service.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getSpecificAccount(@PathVariable long id) {
        var account = service.getSpecificAccountDto(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountDto dto) {
        service.createAccount(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/transaction")
    public ResponseEntity<?> makeTransaction(@RequestBody TransactionDto dto) {
        service.makeTransaction(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable long id) {
        service.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateStatusOfAccount(@RequestBody UpdateStatusDto dto) {
        service.updateStatusOfAccount(dto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
