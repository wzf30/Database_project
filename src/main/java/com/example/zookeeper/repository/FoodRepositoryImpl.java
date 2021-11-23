package com.example.zookeeper.repository;

import com.example.zookeeper.dao.Food;
import com.example.zookeeper.mapper.FoodMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FoodRepositoryImpl implements FoodRepository{
    @Resource
    private FoodMapper foodMapper;

    @Override
    public List<Food> getAllFood() {
        List<Food> foodList = foodMapper.selectAllFood();
        foodList.forEach(
                x -> x.setStorage(Math.round(x.getStorage()*100)/100.0)
        );
        return foodList;
    }

    @Override
    public Food getById(Long id) {
        return foodMapper.selectById(id);
    }

    @Override
    public Food getByName(String name) {
        return foodMapper.selectByName(name);
    }

    @Override
    public void insert(String name, String unit) {
        foodMapper.insert(name, unit);
    }

    @Override
    public void updateById(Long id, String name, String unit, double storage) {
        foodMapper.updateById(id, name, unit, storage);
    }

    @Override
    public void updateStorageById(Long id, double storage) {
        foodMapper.updateStorageById(id, storage);
    }

    @Override
    public void deleteById(long id) {
        foodMapper.deleteById(id);
    }
}
