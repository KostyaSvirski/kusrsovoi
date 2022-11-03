package org.kursovoi.server.util.mapper;

import org.kursovoi.server.dto.CreateUserDto;
import org.kursovoi.server.dto.UserDto;
import org.kursovoi.server.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper
@Component
public interface UserMapper {

    @Mapping(target = "dateOfBirth", source = "user.dateOfBirth", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "role", qualifiedByName = "getRole")
    UserDto map(User user);

    @Mapping(target = "dateOfBirth", qualifiedByName = "getDateToLocalDate")
    User map(CreateUserDto dto);

    @Named("getRole")
    default String getRole(User user) {
        return user.getRole().getRoleName();
    }

    @Named("getDateToLocalDate")
    default LocalDate getDateToLocalDate(CreateUserDto dto) {
        return LocalDate.parse(dto.getDateOfBirth(), DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
