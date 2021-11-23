package com.example.zookeeper.repository;
import com.example.zookeeper.dao.Area;
import com.example.zookeeper.dao.Worker;
import com.example.zookeeper.mapper.WorkerMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class WorkerRepositoryImpl implements WorkerRepository {
    @Resource
    private WorkerMapper workerMapper;

    @Override
    public List<Worker> getAllWorker() {
        return workerMapper.selectAllWorker();
    }

    @Override
    public Worker getById(Long id) {
        return workerMapper.selectById(id);
    }

    @Override
    public void insert(String name, Long age, String gender, Long workingAge, Long teamId, Boolean experienced, Boolean vet) {
        workerMapper.insert(name, age, gender, workingAge, teamId, experienced, vet);
    }

    @Override
    public void updateById(Long id, String name, Long age, String gender, Long workingAge, Long teamId, Boolean experienced, Boolean vet) {
        workerMapper.updateById(id, name, age, gender, workingAge, teamId, experienced, vet);
    }

    @Override
    public void deleteById(Long id) {
        workerMapper.deleteById(id);
    }
}