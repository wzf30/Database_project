package com.example.zookeeper.repository;

import com.example.zookeeper.dao.FeedRecord;
import com.example.zookeeper.mapper.FeedRecordMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository
public class FeedRecordRepositoryImpl implements FeedRecordRepository {
    @Resource
    private FeedRecordMapper feedRecordMapper;

    @Override
    public List<FeedRecord> getAllFeedRecord() {
        return feedRecordMapper.select();
    }

    @Override
    public void insert(Long foodId, Long animalId, double appetite, Date date) {
        feedRecordMapper.insert(foodId, animalId, appetite, date);
    }

    @Override
    public void updateById(Long id, Long foodId, Long animalId, double appetite, Date date) {
        feedRecordMapper.updateById(id, foodId, animalId, appetite, date);
    }

    @Override
    public void deleteById(Long id) {
        feedRecordMapper.deleteById(id);
    }
}
