package com.example.zookeeper.mapper;

import com.example.zookeeper.dao.Therapy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TherapyMapper {
    List<Therapy> selectAllTherapy();

    Therapy selectById(@Param("id") Long id);

    void insert(@Param("animalId") Long animalId,
                @Param("workerId") Long workerId,
                @Param("description") String description,
                @Param("startTime") Date startTime,
                @Param("endTime") Date endTime);

    void updateById(@Param("id") Long id,
                    @Param("animalId") Long animalId,
                    @Param("workerId") Long workerId,
                    @Param("description") String description,
                    @Param("startTime") Date startTime,
                    @Param("endTime") Date endTime);

    void deleteById(@Param("id") Long id);
}
