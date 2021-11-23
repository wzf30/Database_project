package com.example.zookeeper.repository;

import com.example.zookeeper.dao.Therapy;
import com.example.zookeeper.mapper.TherapyMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository
public class TherapyRepositoryImpl implements TherapyRepository{
    @Resource
    private TherapyMapper therapyMapper;

    @Override
    public List<Therapy> getAllTherapy() {
        return therapyMapper.selectAllTherapy();
    }

    @Override
    public Therapy getById(Long id) {
        return therapyMapper.selectById(id);
    }

    @Override
    public void insert(Long animalId, Long workerId, String description, Date startTime, Date endTime) {
        therapyMapper.insert(animalId, workerId, description, startTime, endTime);
    }

    @Override
    public void updateById(Long id, Long animalId, Long workerId, String description, Date startTime, Date endTime) {
        therapyMapper.updateById(id, animalId, workerId, description, startTime, endTime);
    }

    @Override
    public void deleteById(Long id) {
        therapyMapper.deleteById(id);
    }
}
