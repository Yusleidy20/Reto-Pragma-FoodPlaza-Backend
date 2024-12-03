package com.example.foodplaza.domain.api;

import com.example.foodplaza.domain.model.DishModel;

public interface IDishServicePort {
    void saveDish(DishModel dishModel) throws IllegalAccessException;
    void updateDish(DishModel dishModel);
    DishModel getDishById(Long idDish);
}