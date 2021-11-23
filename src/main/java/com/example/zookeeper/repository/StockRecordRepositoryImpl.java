package com.example.zookeeper.repository;

import com.example.zookeeper.dao.StockRecord;
import com.example.zookeeper.mapper.StockRecordMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository
public class StockRecordRepositoryImpl implements StockRecordRepository {
    @Resource
    private StockRecordMapper stockRecordMapper;

    @Override
    public List<StockRecord> getAllStockRecord() {
        return stockRecordMapper.select();
    }

    @Override
    public void addStockRecord(Long foodId, double amount, Long cost, Date date, Date expireDate) {
        stockRecordMapper.insert(foodId, amount, cost, date, expireDate);
    }
}
