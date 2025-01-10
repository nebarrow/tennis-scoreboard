package com.nebarrow.mapper;

import com.nebarrow.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface INewMatchMapper {
    INewMatchMapper INSTANCE = Mappers.getMapper(INewMatchMapper.class);

    INewMatchMapper toDto(Player player);

    Player toEntity(String playerName);
}
