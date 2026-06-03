package com.cine.cinebooking.mapper;

import com.cine.cinebooking.dto.SessionResponse;
import com.cine.cinebooking.entity.MovieSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    @Mapping(target = "movieId", source = "movie.id")
    @Mapping(target = "roomId", source = "room.id")
    @Mapping(target = "roomName", source = "room.name")
    SessionResponse toResponse(MovieSession session);
}
