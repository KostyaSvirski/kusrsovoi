package org.kursovoi.server.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.DepositDto;
import org.kursovoi.server.model.Deposit;
import org.kursovoi.server.repository.DepositRepository;
import org.kursovoi.server.util.exception.DepositNotFoundException;
import org.kursovoi.server.util.mapper.DepositMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
public class DepositeService {

    private final DepositRepository depositRepository;
    private final DepositMapper mapper;

    public List<DepositDto> findAllDeposits() {
        return depositRepository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    public DepositDto findSpecificDepositDto(long id) {
        return mapper.map(findSpecificDeposit(id));
    }

    public void createDeposit(DepositDto dto) {
        depositRepository.save(mapper.map(dto));
    }

    public void deleteDeposit(long id) {
        depositRepository.delete(findSpecificDeposit(id));
    }

    Deposit findSpecificDeposit(long id) {
        return depositRepository.findById(id)
                .orElseThrow(() -> new DepositNotFoundException("Deposit with id: " + id + " - not found!"));
    }
}
