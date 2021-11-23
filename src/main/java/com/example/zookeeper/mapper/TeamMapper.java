package com.example.zookeeper.mapper;

import com.example.zookeeper.dao.Team;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeamMapper {
    List<Team> selectAllTeam();

    Team selectById(@Param("id") Long id);

    Team selectByName(@Param("name") String name);

    void insert(@Param("name") String name,
                @Param("captionId") Long captionId,
                @Param("areaId") Long areaId);

    void updateById(@Param("id") Long id,
                @Param("name") String name,
                @Param("captionId") Long captionId,
                @Param("areaId") Long areaId);

    void deleteById(@Param("id") Long id);
}
