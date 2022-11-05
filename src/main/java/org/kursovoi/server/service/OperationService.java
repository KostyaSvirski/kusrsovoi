package org.kursovoi.server.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.OperationDto;
import org.kursovoi.server.model.Operation;
import org.kursovoi.server.repository.OperationRepository;
import org.kursovoi.server.util.exception.OperationNotFoundException;
import org.kursovoi.server.util.mapper.OperationMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class OperationService {

    private final OperationRepository operationRepository;
    private final OperationMapper mapper;

    @Transactional
    public List<OperationDto> getAllOperations() {
        return operationRepository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional
    public OperationDto getSpecificOperationDto(long id) {
        return mapper.map(getOperation(id));
    }

    @Transactional
    public void createOperation(OperationDto dto) {
        operationRepository.save(mapper.map(dto));
    }

    Operation getOperation(long id) {
        return operationRepository.findById(id)
                .orElseThrow(() -> new OperationNotFoundException("Operation with id: " + id + " - not found"));
    }
}
