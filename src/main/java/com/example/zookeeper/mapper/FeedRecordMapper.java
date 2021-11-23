package com.example.zookeeper.mapper;

import com.example.zookeeper.dao.FeedRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface FeedRecordMapper {
    List<FeedRecord> select();

    void insert(@Param("foodId") Long foodId, @Param("animalId") Long animalId, @Param("appetite") double appetite, @Param("date") Date date);

    void updateById(@Param("id") Long id, @Param("foodId") Long foodId, @Param("animalId") Long animalId, @Param("appetite") double appetite, @Param("date") Date date);

    void deleteById(@Param("id") Long id);
}
