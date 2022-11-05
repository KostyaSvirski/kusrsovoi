package org.kursovoi.server.util.mapper;

import org.kursovoi.server.dto.DepositDto;
import org.kursovoi.server.model.Deposit;
import org.kursovoi.server.model.constant.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface DepositMapper {

    @Mapping(target = "currency", qualifiedByName = "getCurrencyToString")
    DepositDto map(Deposit deposit);

    @Mapping(target = "currency", qualifiedByName = "getCurrencyToEnum")
    Deposit map(DepositDto deposit);

    @Named("getCurrencyToString")
    default String getCurrencyToString(Deposit deposit) {
        return deposit.getCurrency().name();
    }

    @Named("getCurrencyToEnum")
    default Currency getCurrencyToEnum(DepositDto dto) {
        return Currency.valueOf(dto.getCurrency());
    }
}
