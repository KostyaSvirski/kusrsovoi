package org.kursovoi.server.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.CreateDepositDto;
import org.kursovoi.server.dto.DepositOrderDto;
import org.kursovoi.server.dto.UpdateStatusDto;
import org.kursovoi.server.dto.UpdateSumDto;
import org.kursovoi.server.model.DepositOrder;
import org.kursovoi.server.model.constant.DepositOrderStatus;
import org.kursovoi.server.model.constant.Status;
import org.kursovoi.server.repository.DepositOrderRepository;
import org.kursovoi.server.util.exception.IncorrectStatusException;
import org.kursovoi.server.util.exception.ModelNotFoundException;
import org.kursovoi.server.util.mapper.DepositOrderMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class DepositOrderService {

    private final DepositOrderRepository depositOrderRepository;
    private final DepositService depositService;
    private final UserService userService;
    private final DepositOrderMapper mapper;

    @Transactional
    public List<DepositOrderDto> findAllDepositOrders() {
        return depositOrderRepository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional
    public DepositOrderDto findDepositOrder(long id) {
        return mapper.map(getDepositOrder(id));
    }

    @Transactional
    public void updateStatus(UpdateStatusDto dto) {
        var depositOrder = getDepositOrder(dto.getId());
        depositOrder.setStatus(DepositOrderStatus.valueOf(dto.getNewStatus()));
        depositOrderRepository.save(depositOrder);
    }

    @Transactional
    public void createDepositOrder(CreateDepositDto dto) {
        var user = userService.getUser(dto.getIdUser());
        var deposit = depositService.findSpecificDeposit(dto.getIdDeposit());
        var depositOrder = DepositOrder.builder()
                .deposit(deposit)
                .user(user)
                .dateOfIssue(LocalDate.now(ZoneId.of("UTC-3")))
                .sum(dto.getSum())
                .status(DepositOrderStatus.PENDING)
                .build();
        depositOrder.setDateOfEnd(depositOrder.getDateOfIssue().plusMonths(deposit.getMonthToExpire()));
        depositOrderRepository.save(depositOrder);
    }

    @Transactional
    public void updateSum(UpdateSumDto dto) {
        var depositOrder = getDepositOrder(dto.getIdEntity());
        if (!depositOrder.getStatus().equals(DepositOrderStatus.APPROVED) ||
                !depositOrder.getUser().getStatus().equals(Status.ACTIVE)) {
            throw new IncorrectStatusException("Forbidden due to status of user or order of deposit");
        }
        depositOrder.setSum(depositOrder.getSum() + dto.getSum());
        depositOrderRepository.save(depositOrder);
    }

    DepositOrder getDepositOrder(long id) {
        return depositOrderRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Deposit order with id: " + id + " - not found!"));
    }
}
