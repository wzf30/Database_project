package com.example.zookeeper.repository;

import com.example.zookeeper.dao.Worker;

import java.util.List;

public interface WorkerRepository {
    List<Worker> getAllWorker();

    Worker getById(Long id);

    void insert(String name, Long age, String gender, Long workingAge, Long teamId, Boolean experienced, Boolean vet);

    void updateById(Long id, String name, Long age, String gender, Long workingAge, Long teamId, Boolean experienced, Boolean vet);

    void deleteById(Long id);
}
