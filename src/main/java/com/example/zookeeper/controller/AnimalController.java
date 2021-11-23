package com.example.zookeeper.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.zookeeper.dao.Animal;
import com.example.zookeeper.dao.Area;
import com.example.zookeeper.dao.District;
import com.example.zookeeper.dao.Species;
import com.example.zookeeper.po.AnimalPO;
import com.example.zookeeper.po.DistrictPO;
import com.example.zookeeper.repository.AnimalRepository;
import com.example.zookeeper.repository.DistrictRepository;
import com.example.zookeeper.repository.SpeciesRepository;
import com.example.zookeeper.util.StringUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AnimalController {
    @Resource
    private AnimalRepository animalRepository;

    @Resource
    private DistrictRepository districtRepository;

    @Resource
    private SpeciesRepository speciesRepository;

    private List<AnimalPO> convertAnimalListToAnimalPOList(List<Animal> animalList) {
        List<AnimalPO> animalPOList = new ArrayList<>();
        for (Animal animal : animalList) {
            District district = districtRepository.getById(animal.getDistrictId());
            Species species = speciesRepository.getById(animal.getSpeciesId());
            AnimalPO animalPO = new AnimalPO();
            animalPO.setId(animal.getId());
            animalPO.setName(animal.getName());
            animalPO.setAge(animal.getAge());
            animalPO.setGender(animal.getGender());
            animalPO.setSpeciesId(animal.getSpeciesId());
            animalPO.setSpeciesName(species.getName());
            animalPO.setHealthy(animal.getHealthy());
            animalPO.setDistrictName(district.getName());
            animalPOList.add(animalPO);
        }
        return animalPOList;
    }

    @GetMapping("getAllAnimal")
    public JSONObject getAllArea() {
        JSONObject response = new JSONObject();
        List<Animal> animalList = animalRepository.getAllAnimal();
        List<AnimalPO> animalPOList = convertAnimalListToAnimalPOList(animalList);
        response.put("code", 200);
        response.put("animalList", JSON.toJSON(animalPOList));
        response.put("message", "区域查询成功");
        return response;
    }

    @GetMapping("addAnimal")
    public JSONObject addAnimal(@RequestParam String name,
                                @RequestParam Long age,
                                @RequestParam String gender,
                                @RequestParam String speciesName,
                                @RequestParam Boolean healthy,
                                @RequestParam String districtName) {
        JSONObject response = new JSONObject();
        if (StringUtil.isEmpty(name)) {
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "名字不能为空");
            return response;
        }
        if (StringUtil.isEmpty(speciesName)) {
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "物种不能为空");
            return response;
        }
        Species species = speciesRepository.getByName(speciesName);
        District district = districtRepository.getByName(districtName);
        animalRepository.insert(name, age, gender, species.getId(), healthy, district.getId());
        response.put("code", 200);
        response.put("successfullyAdd", true);
        response.put("message", "动物添加成功");
        return response;
    }

    @GetMapping("updateAnimal")
    public JSONObject updateAnimal(@RequestParam Long id,
                                   @RequestParam String name,
                                   @RequestParam Long age,
                                   @RequestParam String gender,
                                   @RequestParam String speciesName,
                                   @RequestParam Boolean healthy,
                                   @RequestParam String districtName) {
        JSONObject response = new JSONObject();
        Species species = speciesRepository.getByName(speciesName);
        District district = districtRepository.getByName(districtName);
        animalRepository.updateById(id, name, age, gender, species.getId(), healthy, district.getId());
        response.put("code", 200);
        response.put("successfullyUpdate", true);
        response.put("message", "动物信息编辑成功");
        return response;
    }

    @GetMapping("deleteAnimal")
    public JSONObject deleteAnimal(@RequestParam Long id) {
        JSONObject response = new JSONObject();
        animalRepository.deleteById(id);
        response.put("code", 200);
        response.put("successfullyDelete", true);
        response.put("message", "动物删除成功");
        return response;
    }
}
