package com.example.zookeeper.repository;
import com.example.zookeeper.dao.District;


import java.util.List;

public interface DistrictRepository {
    List<District> getAllDistrict();

    District getById(Long id);

    District getByName(String name);

    void insert(String name, Long areaId);

    void updateById(Long id, String name, Long areaId);

    void deleteById(Long id);
}
