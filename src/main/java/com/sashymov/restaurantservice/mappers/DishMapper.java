package com.sashymov.restaurantservice.mappers;

import com.sashymov.restaurantservice.models.dto.DishDto;
import com.sashymov.restaurantservice.models.entities.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DishMapper {
    DishMapper INSTANCE = Mappers.getMapper(DishMapper.class);

    Dish toDish (DishDto dishDto);
    DishDto toDto(Dish dish);

    List<Dish> toDishes(List<DishDto> dishDtos);
    List<DishDto> toDtos(List<Dish> dishes);
}
