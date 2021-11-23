package com.example.zookeeper.repository;

import com.example.zookeeper.dao.Area;

import java.util.List;

public interface AreaRepository {
    List<Area> getAllArea();

    Area getById(Long id);

    Area getByAreaName(String areaName);

    void insert(String areaName);

    void updateById(Long id, String newAreaName);

    void deleteById(long id);
}
