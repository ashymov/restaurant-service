package com.sashymov.restaurantservice.dao;

import com.sashymov.restaurantservice.models.entities.Dish;
import com.sashymov.restaurantservice.models.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepo extends JpaRepository<Dish,Long> {
    List<Dish> findAllByRestaurant(Restaurant restaurant);
}
