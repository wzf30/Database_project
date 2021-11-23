package com.example.zookeeper.repository;

import com.example.zookeeper.dao.Team;
import com.example.zookeeper.mapper.TeamMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class TeamRepositoryImpl implements TeamRepository{
    @Resource
    private TeamMapper teamMapper;

    @Override
    public List<Team> getAllTeam() {
        return teamMapper.selectAllTeam();
    }

    @Override
    public Team getById(Long id) {
        return teamMapper.selectById(id);
    }

    @Override
    public Team getByName(String name) {
        return teamMapper.selectByName(name);
    }

    @Override
    public void insert(String name, Long captionId, Long areaId) {
        teamMapper.insert(name, captionId, areaId);
    }

    @Override
    public void updateById(Long id, String name, Long captionId, Long areaId) {
        teamMapper.updateById(id, name, captionId, areaId);
    }

    @Override
    public void deleteById(Long id) {
        teamMapper.deleteById(id);
    }
}
