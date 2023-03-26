package org.kursovoi.server.controller;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.CreateLoanOrderDto;
import org.kursovoi.server.dto.LoanOrderDto;
import org.kursovoi.server.dto.UpdateStatusDto;
import org.kursovoi.server.dto.UpdateSumDto;
import org.kursovoi.server.service.LoanOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loanOrders")
@RequiredArgsConstructor
public class LoanOrderController {

    private final LoanOrderService service;

    @GetMapping
    public ResponseEntity<List<LoanOrderDto>> findAllLoans() {
        var loanOrders = service.findAllLoans();
        return new ResponseEntity<>(loanOrders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanOrderDto> findSpecificLoanOrder(@PathVariable long id) {
        var loanOrder = service.findSpecificLoanOrder(id);
        return new ResponseEntity<>(loanOrder, HttpStatus.OK);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<LoanOrderDto>> findAllPendingLoans() {
        var loanOrders = service.findAllPendingLoans();
        return new ResponseEntity<>(loanOrders, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateStatus(@RequestBody UpdateStatusDto newStatus) {
        service.updateStatus(newStatus);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PatchMapping("/sum")
    public ResponseEntity<?> updateSum(UpdateSumDto dto) {
        service.updateSum(dto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<?> createNewLoanOrder(CreateLoanOrderDto dto) {
        service.createNewLoanOrder(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



}
