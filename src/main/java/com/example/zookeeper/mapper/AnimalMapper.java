package com.example.zookeeper.mapper;

import com.example.zookeeper.dao.Animal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnimalMapper {
    List<Animal> selectAllAnimal();

    Animal selectById(@Param("id") Long id);

    Animal selectByName(@Param("name") String name);

    void insert(@Param("name") String name,
                @Param("age") Long age,
                @Param("gender") String gender,
                @Param("speciesId") Long speciesId,
                @Param("healthy") Boolean healthy,
                @Param("districtId") Long districtId);

    void updateById(@Param("id") Long id,
                    @Param("name") String name,
                    @Param("age") Long age,
                    @Param("gender") String gender,
                    @Param("speciesId") Long speciesId,
                    @Param("healthy") Boolean healthy,
                    @Param("districtId") Long districtId);

    void deleteById(@Param("id") Long id);
}
