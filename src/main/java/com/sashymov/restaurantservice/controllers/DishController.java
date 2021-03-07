package com.sashymov.restaurantservice.controllers;


import com.sashymov.restaurantservice.Response.Response;
import com.sashymov.restaurantservice.models.dto.DishDto;
import com.sashymov.restaurantservice.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @PostMapping("/save")
    public Response save(@RequestBody DishDto dishDto) {
        return dishService.save(dishDto);
    }

    @PutMapping("/update")
    public Response update(@RequestBody DishDto dishDto){
        return dishService.update(dishDto);
    }

    @GetMapping("/findByRestaurant")
    public Response findByRestaurant(@RequestParam Long partnerId) {
        return dishService.findByRestaurant(partnerId);
    }

    @PostMapping("/upload")
    public Response upload(@RequestParam MultipartFile file, @RequestParam Long dishId) {
        return dishService.upload(file, dishId);
    }


}