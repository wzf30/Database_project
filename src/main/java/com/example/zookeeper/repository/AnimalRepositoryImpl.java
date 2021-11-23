package com.example.zookeeper.repository;

import com.example.zookeeper.dao.Animal;
import com.example.zookeeper.mapper.AnimalMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class AnimalRepositoryImpl implements AnimalRepository{
    @Resource
    private AnimalMapper animalMapper;

    @Override
    public List<Animal> getAllAnimal() {
        return animalMapper.selectAllAnimal();
    }

    @Override
    public Animal getById(Long id) {
        return animalMapper.selectById(id);
    }

    @Override
    public Animal getByName(String name) {
        return animalMapper.selectByName(name);
    }

    @Override
    public void insert(String name, Long age, String gender, Long speciesId, Boolean healthy, Long districtId) {
        animalMapper.insert(name, age, gender, speciesId, healthy, districtId);
    }

    @Override
    public void updateById(Long id, String name, Long age, String gender, Long speciesId, Boolean healthy, Long districtId) {
        animalMapper.updateById(id, name, age, gender, speciesId, healthy, districtId);
    }

    @Override
    public void deleteById(Long id) {
        animalMapper.deleteById(id);
    }
}
