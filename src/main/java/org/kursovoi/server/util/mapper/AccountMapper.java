package org.kursovoi.server.util.mapper;

import org.kursovoi.server.dto.AccountDto;
import org.kursovoi.server.model.Account;
import org.kursovoi.server.model.constants.Currency;
import org.kursovoi.server.model.constants.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Mapper
public interface AccountMapper {

    @Mapping(target = "dateOfIssue", source = "account.dateOfIssue", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "currency", qualifiedByName = "getCurrencyToString")
    @Mapping(target = "status", qualifiedByName = "getStatusToString")
    @Mapping(target = "holderId", source = "account.holder.id")
    AccountDto map(Account account);

    @Mapping(target = "dateOfIssue", qualifiedByName = "getDateOfIssue")
    @Mapping(target = "currency", qualifiedByName = "getCurrencyToEnum")
    Account map(AccountDto dto);

    @Named("getCurrencyToString")
    default String getCurrencyToString(Account account) {
        return account.getCurrency().name();
    }

    @Named("getStatusToString")
    default String getStatusToString(Account account) {
        return account.getStatus().name();
    }

    @Named("getCurrencyToEnum")
    default Currency getCurrencyToEnum(AccountDto account) {
        return Currency.valueOf(account.getCurrency());
    }

    @Named("getDateOfIssue")
    default LocalDate getDateOfIssue(AccountDto account) {
        return LocalDate.now(ZoneId.of("UTC+3"));
    }
}
