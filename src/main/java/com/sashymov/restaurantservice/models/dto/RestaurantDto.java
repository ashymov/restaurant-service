package com.sashymov.restaurantservice.models.dto;

import com.sashymov.restaurantservice.models.entities.File;
import lombok.Data;
@Data
public class RestaurantDto {
    private Long id;
    private String name;
    private int dishCount;
    private String hours;
    private String phone;
    private String social;
    private File file;
    private int orderNum;
}
