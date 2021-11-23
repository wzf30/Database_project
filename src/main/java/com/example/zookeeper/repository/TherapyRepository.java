package com.example.zookeeper.repository;

import com.example.zookeeper.dao.District;
import com.example.zookeeper.dao.Therapy;

import java.util.Date;
import java.util.List;

public interface TherapyRepository {
    List<Therapy> getAllTherapy();

    Therapy getById(Long id);

    void insert(Long animalId, Long workerId, String description, Date startTime, Date endTime);

    void updateById(Long id, Long animalId, Long workerId, String description, Date startTime, Date endTime);

    void deleteById(Long id);
}
