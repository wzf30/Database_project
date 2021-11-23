package com.example.zookeeper.repository;

import com.example.zookeeper.dao.Food;

import java.util.List;

public interface FoodRepository {
    List<Food> getAllFood();

    Food getById(Long id);

    Food getByName(String name);

    void insert(String name, String unit);

    void updateById(Long id, String name, String unit, double storage);

    void updateStorageById(Long id, double storage);

    void deleteById(long id);
}
