package org.kursovoi.server.controller;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.ActivateCardDto;
import org.kursovoi.server.dto.CardDto;
import org.kursovoi.server.dto.ChangeStatusOfCardDto;
import org.kursovoi.server.dto.CreateCardDto;
import org.kursovoi.server.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService service;

    @GetMapping
    public ResponseEntity<List<CardDto>> getCardsOfAccount(@RequestParam long idAccount) {
        var cards = service.getCardsOfAccount(idAccount);
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDto> getSpecificCard(@PathVariable long id) {
        var card = service.getSpecificCardDto(id);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCard(@RequestBody CreateCardDto dto) {
        service.createCard(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/activate")
    public ResponseEntity<?> activateCard(@RequestParam ActivateCardDto dto) {
        service.activateCard(dto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<?> changeStatusOfCard(@RequestBody ChangeStatusOfCardDto dto) {
        service.changeStatusOfCard(dto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
