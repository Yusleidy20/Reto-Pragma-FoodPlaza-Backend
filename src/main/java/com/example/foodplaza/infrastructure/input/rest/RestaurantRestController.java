package com.example.foodplaza.infrastructure.input.rest;

import com.example.foodplaza.application.dto.request.RestaurantRequestDto;
import com.example.foodplaza.application.handler.IRestaurantHandlerPort;

import com.example.foodplaza.infrastructure.out.jpa.feignclients.mapper.IRoleFeignClient;
import com.example.foodplaza.infrastructure.out.jpa.feignclients.mapper.IUserFeignClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user-micro/foodplaza")
@RequiredArgsConstructor
public class RestaurantRestController {
    private final IRestaurantHandlerPort restaurantHandlerPort;
    private final IUserFeignClient userFeignClient;
    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);

    private final IRoleFeignClient roleFeignClient;

    @PostMapping("/restaurant")
    @PreAuthorize("hasAuthority('Administrator')")
    public ResponseEntity<String> createRestaurant(@Valid @RequestBody RestaurantRequestDto restaurantRequestDto) {
        log.info("Data received in RestaurantRequestDto: {}", restaurantRequestDto);


        restaurantHandlerPort.saveRestaurant(restaurantRequestDto);
        log.info("Restaurant registered successfully.");
        return new ResponseEntity<>("Restaurant created successfully.", HttpStatus.CREATED);
    }

}