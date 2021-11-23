package com.example.zookeeper.mapper;

import com.example.zookeeper.dao.StockRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface StockRecordMapper {
    List<StockRecord> select();

    void insert(@Param("foodId") Long foodId, @Param("amount") double amount, @Param("cost") Long cost, @Param("time") Date time, @Param("expireTime") Date expireTime);
}
