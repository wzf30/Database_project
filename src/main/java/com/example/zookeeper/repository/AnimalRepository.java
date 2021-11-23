package com.example.zookeeper.repository;

import com.example.zookeeper.dao.Animal;

import java.util.List;

public interface AnimalRepository {
    List<Animal> getAllAnimal();

    Animal getById(Long id);

    Animal getByName(String name);

    void insert(String name, Long age, String gender, Long speciesId, Boolean healthy, Long districtId);

    void updateById(Long id, String name, Long age, String gender, Long speciesId, Boolean healthy, Long districtId);

    void deleteById(Long id);
}
