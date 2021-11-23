package com.example.zookeeper.repository;

import com.example.zookeeper.dao.Area;
import com.example.zookeeper.mapper.AreaMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class AreaRepositoryImpl implements AreaRepository{
    @Resource
    private AreaMapper areaMapper;

    @Override
    public List<Area> getAllArea() {
        return areaMapper.selectAllArea();
    }

    @Override
    public Area getById(Long id) {
        return areaMapper.selectById(id);
    }

    @Override
    public Area getByAreaName(String areaName) {
        return areaMapper.selectByAreaName(areaName);
    }

    @Override
    public void insert(String areaName) {
        areaMapper.insert(areaName);
    }

    @Override
    public void updateById(Long id, String newAreaName) {
        areaMapper.updateById(id, newAreaName);
    }

    @Override
    public void deleteById(long id) {
        areaMapper.deleteById(id);
    }
}
