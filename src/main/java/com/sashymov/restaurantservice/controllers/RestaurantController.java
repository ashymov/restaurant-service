package com.sashymov.restaurantservice.controllers;

import com.sashymov.restaurantservice.Response.Response;
import com.sashymov.restaurantservice.models.dto.RestaurantDto;
import com.sashymov.restaurantservice.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/save")
    public Response save(@RequestBody RestaurantDto restaurantDto){
        return restaurantService.save(restaurantDto);
    }

    @PostMapping("/upload")
    public Response upload(@RequestParam MultipartFile file,@RequestParam Long restaurantId){
        return restaurantService.upload(file,restaurantId);
    }

    @PutMapping("/update")
    public Response update(@RequestBody RestaurantDto restaurantDto){
        return restaurantService.update(restaurantDto);
    }

    @DeleteMapping("/delete")
    public Response delete(@RequestParam Long restId){
        return restaurantService.delete(restId);
    }

    @GetMapping("/findAll")
    public Response findAll(){
        return restaurantService.findAll();
    }
}
