package com.nebarrow.mapper;

import com.nebarrow.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewMatchMapper {

    NewMatchMapper INSTANCE = Mappers.getMapper(NewMatchMapper.class);

    @Mapping(target = "id", ignore = true)
    Player toEntity(String name);
}
