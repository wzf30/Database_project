package com.example.zookeeper.repository;

import com.example.zookeeper.dao.FeedRecord;

import java.util.Date;
import java.util.List;

public interface FeedRecordRepository {
    List<FeedRecord> getAllFeedRecord();

    void insert(Long foodId, Long animalId, double appetite, Date date);

    void updateById(Long id, Long foodId, Long animalId, double appetite, Date date);

    void deleteById(Long id);
}
