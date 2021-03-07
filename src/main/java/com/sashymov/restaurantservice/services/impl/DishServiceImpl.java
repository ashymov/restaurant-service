package com.sashymov.restaurantservice.services.impl;

import com.sashymov.restaurantservice.Response.Response;
import com.sashymov.restaurantservice.dao.DishRepo;
import com.sashymov.restaurantservice.mappers.DishMapper;
import com.sashymov.restaurantservice.mappers.RestaurantMapper;
import com.sashymov.restaurantservice.microservices.fileService.FileResponse;
import com.sashymov.restaurantservice.microservices.fileService.FileServiceFeign;
import com.sashymov.restaurantservice.models.dto.DishDto;
import com.sashymov.restaurantservice.models.dto.RestaurantDto;
import com.sashymov.restaurantservice.models.entities.Dish;
import com.sashymov.restaurantservice.models.entities.File;
import com.sashymov.restaurantservice.models.entities.Restaurant;
import com.sashymov.restaurantservice.services.DishService;
import com.sashymov.restaurantservice.services.FileService;
import com.sashymov.restaurantservice.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Action;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepo dishRepo;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private FileService fileService;
    @Autowired
    private FileServiceFeign fileServiceFeign;


    @Override
    public Response save(DishDto dishDto) {
        Response response = Response.getResponse();
        Dish dish = DishMapper.INSTANCE.toDish(dishDto);
        RestaurantDto restaurantDto = restaurantService.findById(dishDto.getRestaurant().getId());
        dish.setRestaurant(RestaurantMapper.INSTANCE.toRestaurant(restaurantDto));
        dishRepo.save(dish);
        response.setObject(dish);
        return response;
    }

    @Override
    public Response update(DishDto dishDto) {
        Response response = Response.getResponse();

        Dish dish = dishRepo.findById(dishDto.getId()).orElse(null);
        if (dish == null){
            response.setStatus(0);
            response.setMessage("Не найдено!");
            return response;
        }
        dish.setRestaurant(RestaurantMapper.INSTANCE.toRestaurant(dishDto.getRestaurant()));
        dish.setFile(dishDto.getFile());
        dish.setDescription(dishDto.getDescription());
        dish.setId(dishDto.getId());
        dish.setName(dishDto.getName());
        dish.setPrice(dishDto.getPrice());
        dish.setOrderNum(dishDto.getOrderNum());
        dishRepo.save(dish);
        response.setObject(dish);
        return response;
    }

    @Override
    public Response findByRestaurant(Long restaurantId) {
        Response response = Response.getResponse();

        RestaurantDto restaurantDto = restaurantService.findById(restaurantId);
        List<Dish> dishes = dishRepo.findAllByRestaurant(RestaurantMapper.INSTANCE.toRestaurant(restaurantDto));
        dishes = dishes.stream().sorted(Comparator.comparing(Dish::getOrderNum)).collect(Collectors.toList());
        response.setObject(dishes);
        return response;
    }


    @Override
    public Response upload(MultipartFile multipartFile, Long dishId) {
        Response response = Response.getResponse();

        Dish dish = dishRepo.findById(dishId).orElse(null);
        if (dish==null){
            response.setStatus(0);
            response.setMessage("Не найдено!");
            return response;
        }
        FileResponse fileResponse = fileServiceFeign.upload(multipartFile);

        File file = new File();
        file.setFileDownloadUri(fileResponse.getDownloadUri());
        file.setFileName(fileResponse.getFileName());
        file.setFileType(fileResponse.getFileType());
        file.setSize(fileResponse.getSize());

        file = fileService.save(file);
        dish.setFile(file);
        dishRepo.save(dish);
        response.setObject(dish);
        return response;


    }


}
