package com.nebarrow.mapper;

import com.nebarrow.dto.request.MatchRequest;
import com.nebarrow.dto.response.MatchResponse;
import com.nebarrow.entity.Match;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchMapper {

    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "playerOne.id", source = "playerOne")
    @Mapping(target = "playerTwo.id", source = "playerTwo")
    @Mapping(target = "winner.id", source = "winner")
    Match toEntity(MatchRequest request);

    @Mapping(target = "playerOne", source = "playerOne.name")
    @Mapping(target = "playerTwo", source = "playerTwo.name")
    @Mapping(target = "winner", source = "winner.name")
    MatchResponse toDto(Match match);
}
