package com.example.zookeeper.mapper;

import com.example.zookeeper.dao.Worker;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkerMapper {
    List<Worker> selectAllWorker();

    Worker selectById(@Param("id") Long id);

    void insert(@Param("name") String name,
                @Param("age") Long age,
                @Param("gender") String gender,
                @Param("workingAge") Long workingAge,
                @Param("teamId") Long teamId,
                @Param("experienced") Boolean experienced,
                @Param("vet") Boolean vet);

    void updateById(@Param("id") Long id,
                    @Param("name") String name,
                    @Param("age") Long age,
                    @Param("gender") String gender,
                    @Param("workingAge") Long workingAge,
                    @Param("teamId") Long teamId,
                    @Param("experienced") Boolean experienced,
                    @Param("vet") Boolean vet);

    void deleteById(@Param("id") Long id);
}
