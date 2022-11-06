package org.kursovoi.server.util.mapper;

import org.kursovoi.server.dto.DepositOrderDto;
import org.kursovoi.server.model.DepositOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface DepositOrderMapper {

    @Mapping(target = "dateOfIssue", source = "dateOfIssue", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "status", qualifiedByName = "getStatusToString")
    @Mapping(target = "idDeposit", source = "deposit.id")
    @Mapping(target = "idUser", source = "user.id")
    @Mapping(target = "dateOfEnd", source = "dateOfEnd", dateFormat = "yyyy-MM-dd")
    DepositOrderDto map(DepositOrder model);

    @Named("getStatusToString")
    default String getStatusToString(DepositOrder model) {
        return model.getStatus().name();
    }
}
