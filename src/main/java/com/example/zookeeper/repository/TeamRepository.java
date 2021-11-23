package com.example.zookeeper.repository;

import com.example.zookeeper.dao.Area;
import com.example.zookeeper.dao.Team;

import java.util.List;

public interface TeamRepository {
    List<Team> getAllTeam();

    Team getById(Long id);

    Team getByName(String areaName);

    void insert(String areaName, Long captionId, Long areaId);

    void updateById(Long id, String newAreaName, Long captionId, Long areaId);

    void deleteById(Long id);
}
