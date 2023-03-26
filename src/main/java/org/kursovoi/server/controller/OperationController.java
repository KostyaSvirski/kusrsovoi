package org.kursovoi.server.controller;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.OperationDto;
import org.kursovoi.server.service.OperationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operations")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService service;

    @GetMapping
    public ResponseEntity<List<OperationDto>> getAllOperations() {
        var operations = service.getAllOperations();
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationDto> getSpecificOperationDto(@PathVariable long id) {
        var operation = service.getSpecificOperationDto(id);
        return new ResponseEntity<>(operation, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createOperation(@RequestBody OperationDto dto) {
        service.createOperation(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
