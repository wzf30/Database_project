package com.example.zookeeper.po;

public class WorkerPO {
    Long id;
    String name;
    Long age;
    String gender;
    Long workingAge;
    String teamName;
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

    public Long getAge() {
        return age;
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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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
}
