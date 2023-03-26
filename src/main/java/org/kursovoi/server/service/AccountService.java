package org.kursovoi.server.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.AccountDto;
import org.kursovoi.server.dto.TransactionDto;
import org.kursovoi.server.dto.UpdateStatusDto;
import org.kursovoi.server.model.Account;
import org.kursovoi.server.model.User;
import org.kursovoi.server.model.constant.Status;
import org.kursovoi.server.repository.AccountRepository;
import org.kursovoi.server.util.exception.AccountInvalidException;
import org.kursovoi.server.util.exception.IncorrectStatusException;
import org.kursovoi.server.util.exception.ModelNotFoundException;
import org.kursovoi.server.util.exception.TransactionSumTooLargeException;
import org.kursovoi.server.util.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private UserService userService;
    private final AccountMapper mapper;

    @Transactional
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional
    public List<AccountDto> getAccountsOfUser(User user) {
        return accountRepository.findByHolder(user).stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional
    public void createAccount(AccountDto dto) {
        Account newAccount = mapper.map(dto);
        newAccount.setStatus(Status.ACTIVE);
        User user = userService.getUser(dto.getHolderId());
        newAccount.setHolder(user);
        accountRepository.saveAndFlush(newAccount);
    }

    @Transactional
    public void deleteAccount(long idAccount) {
        accountRepository.delete(getSpecificAccount(idAccount));
    }

    @Transactional
    public void makeTransaction(TransactionDto transaction) {
        Account accountFrom = getSpecificAccount(transaction.getIdFrom());
        Account accountTo = getSpecificAccount(transaction.getIdTo());
        if (!accountFrom.getStatus().equals(Status.ACTIVE) || !accountTo.getStatus().equals(Status.ACTIVE)) {
            throw new AccountInvalidException("One of accounts is not active");
        }
        if (accountFrom.getSum() < transaction.getSum()) {
            throw new TransactionSumTooLargeException("Transaction sum too big");
        }

        accountFrom.setSum(accountFrom.getSum() - transaction.getSum());
        accountTo.setSum(accountTo.getSum() + transaction.getSum());

        accountRepository.save(accountTo);
        accountRepository.save(accountFrom);
    }

    @Transactional
    public void updateStatusOfAccount(UpdateStatusDto dto) {
        if (Arrays.stream(Status.values()).map(Enum::name).noneMatch(status -> status.equals(dto.getNewStatus()))) {
            throw new IncorrectStatusException("Status is incorrect");
        }
        var account = getSpecificAccount(dto.getId());
        account.setStatus(Status.valueOf(dto.getNewStatus()));
        accountRepository.save(account);
    }

    @Transactional
    Account getSpecificAccount(long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Account with id " + id + " not found!"));
    }

    @Transactional
    public AccountDto getSpecificAccountDto(long id) {
        return mapper.map(getSpecificAccount(id));
    }


}
