package org.kursovoi.server.util.mapper;

import org.kursovoi.server.dto.CreateUserDto;
import org.kursovoi.server.dto.UserDto;
import org.kursovoi.server.model.Role;
import org.kursovoi.server.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "dateOfBirth", source = "dateOfBirth", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "role", source = "role", qualifiedByName = "getRole")
    public abstract UserDto map(User user);

    @Mapping(target = "dateOfBirth", source = "dateOfBirth", qualifiedByName = "getDateToLocalDate")
    public abstract User map(CreateUserDto dto);

    @Named("getRole")
    public String getRole(Role user) {
        return user.getRoleName();
    }

    @Named("getDateToLocalDate")
    public LocalDate getDateToLocalDate(String dto) {
        return LocalDate.parse(dto, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
