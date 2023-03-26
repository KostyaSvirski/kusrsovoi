package org.kursovoi.server.controller;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.CreateDepositDto;
import org.kursovoi.server.dto.DepositOrderDto;
import org.kursovoi.server.dto.UpdateStatusDto;
import org.kursovoi.server.dto.UpdateSumDto;
import org.kursovoi.server.service.DepositOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depositOrders")
@RequiredArgsConstructor
public class DepositOrderController {

    private final DepositOrderService service;

    @GetMapping
    public ResponseEntity<List<DepositOrderDto>> findAllDepositOrders() {
        var depositOrders = service.findAllDepositOrders();
        return new ResponseEntity<>(depositOrders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepositOrderDto> findSpecificOrder(@PathVariable long id) {
        var depositOrder = service.findDepositOrder(id);
        return new ResponseEntity<>(depositOrder, HttpStatus.OK);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<DepositOrderDto>> findAllPendingDeposits() {
        var depositOrders = service.findAllPendingDeposits();
        return new ResponseEntity<>(depositOrders, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateStatus(UpdateStatusDto dto) {
        service.updateStatus(dto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<?> createDepositOrder(CreateDepositDto dto) {
        service.createDepositOrder(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/sum")
    public ResponseEntity<?> updateSum(UpdateSumDto dto) {
        service.updateSum(dto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
