package org.kursovoi.server.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.CreateLoanOrderDto;
import org.kursovoi.server.dto.LoanOrderDto;
import org.kursovoi.server.dto.UpdateStatusDto;
import org.kursovoi.server.model.LoanOrder;
import org.kursovoi.server.model.constant.LoanOrderStatus;
import org.kursovoi.server.repository.LoanOrderRepository;
import org.kursovoi.server.util.exception.ModelNotFoundException;
import org.kursovoi.server.util.mapper.LoanOrderMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
public class LoanOrderService {

    private final LoanOrderRepository loanOrderRepository;
    private final LoanService loanService;
    private final UserService userService;
    private final LoanOrderMapper mapper;

    @Transactional
    public List<LoanOrderDto> findAllLoans() {
        return loanOrderRepository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional
    public LoanOrderDto findSpecificLoanOrder(long id) {
        return mapper.map(findLoanOrder(id));
    }

    @Transactional
    public void updateStatus(UpdateStatusDto newStatus) {
        var loanOrder = findLoanOrder(newStatus.getId());
        loanOrder.setStatus(LoanOrderStatus.valueOf(newStatus.getNewStatus()));
        loanOrderRepository.save(loanOrder);
    }

    @Transactional
    public void createNewLoanOrder(CreateLoanOrderDto dto) {
        var loan = loanService.findSpecificLoan(dto.getIdLoan());
        var user = userService.getUser(dto.getIdUser());
        var newLoanOrder = LoanOrder.builder()
                .loan(loan)
                .user(user)
                .dateOfIssue(LocalDate.now(ZoneId.of("UTC+3")))
                .status(LoanOrderStatus.PENDING)
                .sum(dto.getSum())
                .build();
        newLoanOrder.setDateOfEnd(newLoanOrder.getDateOfIssue().plusMonths(loan.getMonthsToReturn()));
        loanOrderRepository.save(newLoanOrder);
    }

    LoanOrder findLoanOrder(long id) {
        return loanOrderRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Loan order with id: " + id + " - not found!"));
    }


}
