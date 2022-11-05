package org.kursovoi.server.util.mapper;

import org.kursovoi.server.dto.LoanDto;
import org.kursovoi.server.model.Loan;
import org.kursovoi.server.model.constant.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LoanMapper {

    @Mapping(target = "currency", qualifiedByName = "getCurrencyToString")
    LoanDto map(Loan loan);

    @Mapping(target = "currency", qualifiedByName = "getCurrencyToEnum")
    Loan map(LoanDto loan);

    @Named("getCurrencyToString")
    default String getCurrencyToString(Loan loan) {
        return loan.getCurrency().name();
    }

    @Named("getCurrencyToEnum")
    default Currency getCurrencyToEnum(LoanDto loan) {
        return Currency.valueOf(loan.getCurrency());
    }
}
