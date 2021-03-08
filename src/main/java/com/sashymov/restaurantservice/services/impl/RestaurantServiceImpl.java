package com.sashymov.restaurantservice.services.impl;

import com.sashymov.restaurantservice.Response.Response;
import com.sashymov.restaurantservice.dao.RestaurantRepo;
import com.sashymov.restaurantservice.mappers.RestaurantMapper;
import com.sashymov.restaurantservice.microservices.fileService.FileResponse;
import com.sashymov.restaurantservice.microservices.fileService.FileServiceFeign;
import com.sashymov.restaurantservice.models.dto.RestaurantDto;
import com.sashymov.restaurantservice.models.entities.File;
import com.sashymov.restaurantservice.models.entities.Restaurant;
import com.sashymov.restaurantservice.services.FileService;
import com.sashymov.restaurantservice.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepo restaurantRepo;
    @Autowired
    private FileServiceFeign fileServiceFeign;
    @Autowired
    private FileService fileService;

    @Override
    public Response save(RestaurantDto restaurantDto) {
        Response response = Response.getResponse();
        Restaurant restaurant = RestaurantMapper.INSTANCE.toRestaurant(restaurantDto);
        restaurantRepo.save(restaurant);
        response.setObject(restaurant);
        return response;
    }

    @Override
    public Response upload(MultipartFile multipartFile, Long restaurantId) {
        Response response = Response.getResponse();

        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElse(null);
        if (restaurant == null){
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
        restaurant.setFile(file);

        restaurantRepo.save(restaurant);

        response.setObject(restaurant);

        return response;
    }

    @Override
    public Response update(RestaurantDto restaurantDto) {
        Response response = Response.getResponse();
        Restaurant restaurant = restaurantRepo.findById(restaurantDto.getId()).orElse(null);
        if (restaurant == null){
            response.setStatus(0);
            response.setMessage("Не найдено!");
            return response;
        }
        restaurant.setFile(restaurantDto.getFile());
        restaurant.setDishCount(restaurantDto.getDishCount());
        restaurant.setHours(restaurantDto.getHours());
        restaurant.setId(restaurantDto.getId());
        restaurant.setName(restaurantDto.getName());
        restaurant.setPhone(restaurantDto.getPhone());
        restaurant.setSocial(restaurantDto.getSocial());
        restaurantRepo.save(restaurant);
        response.setObject(restaurant);
        return response;
    }

    @Override
    public Response delete(Long restId) {
        Response response = Response.getResponse();
        Restaurant restaurant = restaurantRepo.findById(restId).orElse(null);
        if (restaurant == null){
            response.setStatus(0);
            response.setMessage("Не найдено!");
            return  response;
        }
        restaurantRepo.delete(restaurant);
        return response;
    }

    @Override
    public RestaurantDto findById(Long restaurantId) {

        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElse(null);
        if (restaurant == null) {

            throw new RuntimeException("Не найдено!");
        }
        return RestaurantMapper.INSTANCE.toDto(restaurant);
    }

    @Override
    public Response findAll() {
        Response response = Response.getResponse();
        List<Restaurant> restaurants = restaurantRepo.findAll();
        restaurants = restaurants.stream().sorted(Comparator.comparing(Restaurant::getOrderNum)).collect(Collectors.toList());
        response.setObject(restaurants);
        return response;
    }
}
