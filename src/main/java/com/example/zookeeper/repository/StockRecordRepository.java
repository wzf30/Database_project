package com.example.zookeeper.repository;

import com.example.zookeeper.dao.StockRecord;

import java.util.Date;
import java.util.List;

public interface StockRecordRepository {
    List<StockRecord> getAllStockRecord();

    void addStockRecord(Long foodId, double amount, Long cost, Date date, Date expireDate);
}
