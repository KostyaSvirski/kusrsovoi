package org.kursovoi.server.util.mapper;

import org.kursovoi.server.dto.LoanOrderDto;
import org.kursovoi.server.model.LoanOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LoanOrderMapper {

    @Mapping(target = "dateOfIssue", source = "dateOfIssue", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "status", qualifiedByName = "getStatusToString")
    @Mapping(target = "idLoan", source = "loan.id")
    @Mapping(target = "dateOfEnd", source = "dateOfEnd", dateFormat = "yyyy-MM-dd")
    LoanOrderDto map(LoanOrder model);

    @Named("getStatusToString")
    default String getStatusToString(LoanOrder model) {
        return model.getStatus().name();
    }
}
