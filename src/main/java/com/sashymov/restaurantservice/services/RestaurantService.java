package com.sashymov.restaurantservice.services;

import com.sashymov.restaurantservice.Response.Response;
import com.sashymov.restaurantservice.models.dto.RestaurantDto;
import org.springframework.web.multipart.MultipartFile;

public interface RestaurantService {

    Response save (RestaurantDto restaurantDto);
    Response upload (MultipartFile multipartFile, Long restaurantId);
    Response update(RestaurantDto restaurantDto);
    Response delete(Long restId);
    RestaurantDto findById(Long restaurantId);
    Response findAll();

}
