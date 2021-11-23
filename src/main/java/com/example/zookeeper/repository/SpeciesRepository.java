package com.example.zookeeper.repository;

import com.example.zookeeper.dao.Species;

import java.util.List;

public interface SpeciesRepository {
    List<Species> getAllSpecies();

    Species getById(Long id);

    Species getByName(String name);

    void insert(String name, Long life, String level, String habit, String habitat);

    void updateById(Long id, String name, Long life, String level, String habit, String habitat);

    void deleteById(long id);
}
