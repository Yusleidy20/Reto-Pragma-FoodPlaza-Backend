package com.example.foodplaza.infrastructure.input.rest;

import com.example.foodplaza.application.dto.request.RestaurantRequestDto;
import com.example.foodplaza.application.dto.response.RestaurantDto;
import com.example.foodplaza.application.dto.response.RestaurantResponseDto;
import com.example.foodplaza.application.handler.IRestaurantHandlerPort;

import com.example.foodplaza.infrastructure.out.jpa.feignclients.mapper.IRoleFeignClient;
import com.example.foodplaza.infrastructure.out.jpa.feignclients.mapper.IUserFeignClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
@RequestMapping(path = "/user-micro/foodplaza")
@RequiredArgsConstructor
public class RestaurantRestController {
    private final IRestaurantHandlerPort restaurantHandlerPort;
    private final IUserFeignClient userFeignClient;

    private final IRoleFeignClient roleFeignClient;

    @PostMapping("/restaurant")
    @PreAuthorize("hasAuthority('Administrator')")
    public ResponseEntity<String> createRestaurant(@Valid @RequestBody RestaurantRequestDto restaurantRequestDto) {
        restaurantHandlerPort.saveRestaurant(restaurantRequestDto);
        return new ResponseEntity<>("Restaurant created successfully.", HttpStatus.CREATED);
    }



    // Endpoint para listar los restaurantes con paginación (solo para clientes)
    @GetMapping("/restaurants")
    @PreAuthorize("hasAuthority('Customer')") // Solo usuarios con el rol 'Customer' pueden acceder
    public ResponseEntity<List<RestaurantDto>> getRestaurants(
            @RequestParam int page,
            @RequestParam int size) {
        // Llamar al handler para obtener los restaurantes con paginación
        Page<RestaurantDto> restaurantsPage = restaurantHandlerPort.getRestaurants(page, size);
        return new ResponseEntity<>(restaurantsPage.getContent(), HttpStatus.OK);
    }
    @GetMapping("/restaurant/{idRestaurant}")
    @PreAuthorize("hasAuthority('Owner')") // Solo usuarios con el rol 'Owner' pueden acceder
    public ResponseEntity<RestaurantResponseDto> getRestaurantById(@PathVariable Long idRestaurant) {
        RestaurantResponseDto restaurant = restaurantHandlerPort.getRestaurantById(idRestaurant);
        return ResponseEntity.ok(restaurant);
    }
    @GetMapping("/restaurant/{idRestaurant}/orders")
    @PreAuthorize("hasAuthority('Owner')") // Solo usuarios con el rol 'Customer' pueden acceder
    public ResponseEntity<List<Long>> getOrderIdsByRestaurantId(@PathVariable Long idRestaurant) {

        // Llamar al servicio para obtener los IDs de los pedidos asociados a este restaurante
        List<Long> orderIds = restaurantHandlerPort.getOrderIdsByRestaurantId(idRestaurant);

        return ResponseEntity.ok(orderIds);
    }


}