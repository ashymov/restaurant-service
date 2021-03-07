package com.sashymov.restaurantservice.models.dto;

import com.sashymov.restaurantservice.models.entities.File;
import lombok.Data;

@Data
public class DishDto {
    private Long id;
    private String name;
    private File file;
    private int price;
    private RestaurantDto restaurant;
    private String description;
    private int orderNum;


}
