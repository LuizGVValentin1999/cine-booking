package com.cine.cinebooking.mapper;

import com.cine.cinebooking.dto.MovieResponse;
import com.cine.cinebooking.entity.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieResponse toResponse(Movie movie);
}
