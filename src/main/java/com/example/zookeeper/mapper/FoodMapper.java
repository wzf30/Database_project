package com.example.zookeeper.mapper;

import com.example.zookeeper.dao.Food;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FoodMapper {
    List<Food> selectAllFood();

    Food selectById(@Param("id") Long id);

    Food selectByName(@Param("name") String name);

    void insert(@Param("name") String name, @Param("unit") String unit);

    void updateById(@Param("id") Long id, @Param("name") String name, @Param("unit") String unit, @Param("storage") double storage);

    void updateStorageById(@Param("id") Long id, @Param("storage") double storage);

    void deleteById(@Param("id") Long id);
}
