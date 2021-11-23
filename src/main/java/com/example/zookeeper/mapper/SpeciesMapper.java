package com.example.zookeeper.mapper;

import com.example.zookeeper.dao.Species;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpeciesMapper {
    List<Species> selectAllSpecies();

    Species selectById(@Param("id") Long id);

    Species selectByName(@Param("name") String name);

    void insert(@Param("name") String name,
                @Param("life") Long life,
                @Param("level") String level,
                @Param("habit") String habit,
                @Param("habitat") String habitat);

    void updateById(@Param("id") Long id,
                    @Param("name") String name,
                    @Param("life") Long life,
                    @Param("level") String level,
                    @Param("habit") String habit,
                    @Param("habitat") String habitat);

    void deleteById(@Param("id") Long id);
}
