package com.example.zookeeper.repository;

import com.example.zookeeper.dao.Species;
import com.example.zookeeper.mapper.SpeciesMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class SpeciesRepositoryImpl implements SpeciesRepository{
    @Resource
    private SpeciesMapper speciesMapper;

    @Override
    public List<Species> getAllSpecies() {
        return speciesMapper.selectAllSpecies();
    }

    @Override
    public Species getById(Long id) {
        return speciesMapper.selectById(id);
    }

    @Override
    public Species getByName(String name) {
        return speciesMapper.selectByName(name);
    }

    @Override
    public void insert(String name, Long life, String level, String habit, String habitat) {
        speciesMapper.insert(name, life, level, habit, habitat);
    }

    @Override
    public void updateById(Long id, String name, Long life, String level, String habit, String habitat) {
        speciesMapper.updateById(id, name, life, level, habit, habitat);
    }

    @Override
    public void deleteById(long id) {
        speciesMapper.deleteById(id);
    }
}
