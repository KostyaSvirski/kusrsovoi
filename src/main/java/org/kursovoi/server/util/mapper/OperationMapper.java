package org.kursovoi.server.util.mapper;

import org.kursovoi.server.dto.OperationDto;
import org.kursovoi.server.model.Operation;
import org.kursovoi.server.model.constant.OperationType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface OperationMapper {

    @Mapping(target = "type", qualifiedByName = "getTypeToString")
    OperationDto map(Operation model);

    @Mapping(target = "type", qualifiedByName = "getTypeToEnum")
    Operation map(OperationDto dto);

    @Named("getTypeToString")
    default String getTypeToString(Operation model) {
        return model.getType().name();
    }

    @Named("getTypeToEnum")
    default OperationType getTypeToEnum(OperationDto dto) {
        return OperationType.valueOf(dto.getType());
    }
}
