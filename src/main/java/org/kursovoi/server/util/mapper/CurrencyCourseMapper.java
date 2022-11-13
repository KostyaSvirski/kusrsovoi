package org.kursovoi.server.util.mapper;

import org.kursovoi.server.dto.CurrencyCourseDto;
import org.kursovoi.server.dto.UpdateCurrencyCourseDto;
import org.kursovoi.server.model.CurrencyCourse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CurrencyCourseMapper {

    @Mapping(target = "date", source = "date", dateFormat = "yyyy-MM-dd")
    CurrencyCourseDto map(CurrencyCourse model);

    CurrencyCourse map(UpdateCurrencyCourseDto dto);
}
