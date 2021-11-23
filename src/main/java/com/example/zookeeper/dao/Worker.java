package com.example.zookeeper.dao;

public class Worker {
    Long id;
    String name;
    Long age;
    String gender;
    Long workingAge;
    Long teamId;
    Boolean experienced;
    Boolean vet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getWorkingAge() {
        return workingAge;
    }

    public void setWorkingAge(Long workingAge) {
        this.workingAge = workingAge;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Boolean getExperienced() {
        return experienced;
    }

    public void setExperienced(Boolean experienced) {
        this.experienced = experienced;
    }

    public Boolean getVet() {
        return vet;
    }

    public void setVet(Boolean vet) {
        this.vet = vet;
    }

    public Long getAge() {
        return age;
    }
}
