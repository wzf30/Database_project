package com.example.zookeeper.mapper;

import com.example.zookeeper.dao.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AreaMapper {
    List<Area> selectAllArea();

    Area selectById(@Param("id") Long id);

    Area selectByAreaName(@Param("areaName") String areaName);

    void insert(@Param("areaName") String areaName);

    void updateById(@Param("id") Long id, @Param("newAreaName") String newAreaName);

    void deleteById(@Param("id") Long id);
}
