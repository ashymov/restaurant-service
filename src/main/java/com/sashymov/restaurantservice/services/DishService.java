package com.sashymov.restaurantservice.services;

import com.sashymov.restaurantservice.Response.Response;
import com.sashymov.restaurantservice.models.dto.DishDto;
import org.springframework.web.multipart.MultipartFile;

public interface DishService {
    Response save(DishDto dishDto);
    Response update(DishDto dishDto);
    Response upload(MultipartFile multipartFile,Long dishId);
    Response delete(Long dishId);
    Response findByRestaurant(Long restaurantId);
}
