package com.sashymov.restaurantservice.mappers;

import com.sashymov.restaurantservice.models.dto.RestaurantDto;
import com.sashymov.restaurantservice.models.entities.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    Restaurant toRestaurant(RestaurantDto restaurantDto);
    RestaurantDto toDto(Restaurant restaurant);
}
