package com.example.zookeeper.mapper;

import com.example.zookeeper.dao.Area;
import com.example.zookeeper.dao.District;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrictMapper {
    List<District> selectAllDistrict();

    District selectByName(@Param("name") String name);

    District selectById(@Param("id") Long id);

    void insert(@Param("name") String name,
                @Param("areaId") Long areaId);

    void updateById(@Param("id") Long id,
                    @Param("name") String name,
                    @Param("areaId") Long areaId);

    void deleteById(@Param("id") Long id);
}
