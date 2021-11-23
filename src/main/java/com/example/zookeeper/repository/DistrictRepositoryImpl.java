package com.example.zookeeper.repository;

import com.example.zookeeper.dao.District;
import com.example.zookeeper.mapper.DistrictMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {
    @Resource
    private DistrictMapper districtMapper;

    @Override
    public List<District> getAllDistrict() {
        return districtMapper.selectAllDistrict();
    }

    @Override
    public District getById(Long id) {
        return districtMapper.selectById(id);
    }

    @Override
    public District getByName(String name) {
        return districtMapper.selectByName(name);
    }

    @Override
    public void insert(String name, Long areaId) {
        districtMapper.insert(name, areaId);
    }

    @Override
    public void updateById(Long id, String name, Long areaId) {
        districtMapper.updateById(id, name, areaId);
    }

    @Override
    public void deleteById(Long id) {
        districtMapper.deleteById(id);
    }
}
