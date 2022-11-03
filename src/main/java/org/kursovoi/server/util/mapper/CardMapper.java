package org.kursovoi.server.util.mapper;

import org.kursovoi.server.dto.CardDto;
import org.kursovoi.server.dto.CreateCardDto;
import org.kursovoi.server.model.Card;
import org.kursovoi.server.model.constant.CardIssuer;
import org.kursovoi.server.model.constant.CardType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;

@Mapper
@Component
public interface CardMapper {

    @Mapping(target = "dateOfExpire", source = "card.dateOfExpire", dateFormat = "MM/yyyy")
    @Mapping(target = "status", qualifiedByName = "getStatusToString")
    @Mapping(target = "cardIssuer", qualifiedByName = "getCardIssuerToString")
    @Mapping(target = "type", qualifiedByName = "getTypeToString")
    CardDto map(Card card);

    @Mapping(target = "dateOfExpire", qualifiedByName = "getDateOfExpire")
    @Mapping(target = "cardIssuer", qualifiedByName = "getCardIssuerToEnum")
    @Mapping(target = "type", qualifiedByName = "getTypeToEnum")
    Card map(CreateCardDto card);

    @Named("getStatusToString")
    default String getStatusToString(Card card) {
        return card.getStatus().name();
    }

    @Named("getCardIssuerToString")
    default String getCardIssuerToString(Card card) {
        return card.getCardIssuer().name();
    }

    @Named("getTypeToString")
    default String getTypeToString(Card card) {
        return card.getType().name();
    }

    @Named("getDateOfExpire")
    default LocalDate getDateOfExpire() {
        return LocalDate.now(ZoneId.of("UTC+3")).plusYears(4);
    }

    @Named("getTypeToEnum")
    default CardType getTypeToEnum(CreateCardDto dto) {
        return CardType.valueOf(dto.getType());
    }

    @Named("getCardIssuerToEnum")
    default CardIssuer getCardIssuerToEnum(CreateCardDto dto) {
        return CardIssuer.valueOf(dto.getCardIssuer());
    }
}
