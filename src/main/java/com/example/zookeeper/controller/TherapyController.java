package com.example.zookeeper.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.zookeeper.dao.*;
import com.example.zookeeper.po.AnimalPO;
import com.example.zookeeper.po.TherapyPO;
import com.example.zookeeper.repository.*;
import com.example.zookeeper.util.DateUtil;
import com.example.zookeeper.util.StringUtil;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class TherapyController {
    @Resource
    private TherapyRepository therapyRepository;
    @Resource
    private SpeciesRepository speciesRepository;
    @Resource
    private DistrictRepository districtRepository;
    @Resource
    private AnimalRepository animalRepository;
    @Resource
    private WorkerRepository workerRepository;

    private AnimalPO convertAnimalToAnimalPO(Animal animal) {
        AnimalPO animalPO = new AnimalPO();
        Species species = speciesRepository.getById(animal.getSpeciesId());
        District district = districtRepository.getById(animal.getDistrictId());
        animalPO.setId(animal.getId());
        animalPO.setName(animal.getName());
        animalPO.setAge(animal.getAge());
        animalPO.setGender(animal.getGender());
        animalPO.setSpeciesName(species.getName());
        animalPO.setHealthy(animal.getHealthy());
        animalPO.setDistrictName(district.getName());
        return animalPO;
    }

    private List<TherapyPO> convertTherapyListToTherapyPOList(List<Therapy> therapyList) {
        List<TherapyPO> therapyPOList = new ArrayList<>();
        for (Therapy therapy : therapyList) {
            TherapyPO therapyPO = new TherapyPO();
            Animal animal = animalRepository.getById(therapy.getAnimalId());
            AnimalPO animalPO = convertAnimalToAnimalPO(animal);
            Worker worker = workerRepository.getById(therapy.getWorkerId());
            therapyPO.setId(therapy.getId());
            therapyPO.setAnimalId(therapy.getAnimalId());
            therapyPO.setAnimalName(animalPO.getName());
            therapyPO.setSpeciesName(animalPO.getSpeciesName());
            therapyPO.setWorkerId(therapy.getWorkerId());
            therapyPO.setWorkerName(worker.getName());
            therapyPO.setDescription(therapy.getDescription());
            therapyPO.setStartTime(DateUtil.convertStrFromDate(therapy.getStartTime()));
            therapyPO.setEndTime(DateUtil.convertStrFromDate(therapy.getEndTime()));
            therapyPOList.add(therapyPO);
        }
        return therapyPOList;
    }

    @GetMapping("getAllTherapy")
    public JSONObject getAllTherapy() {
        JSONObject response = new JSONObject();
        List<Therapy> therapyList = therapyRepository.getAllTherapy();
        List<TherapyPO> therapyPOList = convertTherapyListToTherapyPOList(therapyList);
        response.put("code", 200);
        response.put("therapyList", JSON.toJSON(therapyPOList));
        response.put("message", "治疗记录查询成功");
        return response;
    }

    @GetMapping("addTherapy")
    public JSONObject addTherapy(@RequestParam String stringAnimalId,
                                 @RequestParam String stringWorkerId,
                                 @RequestParam String description,
                                 @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date startTime,
                                 @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date endTime) {
        JSONObject response = new JSONObject();
        if (StringUtil.isEmpty(stringAnimalId)) {
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "请选择就诊动物");
            return response;
        }
        Long animalId = Long.parseLong(stringAnimalId);

        if (StringUtil.isEmpty(stringWorkerId)) {
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "请选择治疗人员");
            return response;
        }
        Long workerId = Long.parseLong(stringWorkerId);

        if(startTime.compareTo(endTime) > 0){
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "开始时间不能晚于结束时间");
            return response;
        }

        therapyRepository.insert(animalId, workerId,description, startTime, endTime);
        response.put("code", 200);
        response.put("successfullyAdd", true);
        response.put("message", "治疗记录添加成功");
        return response;
    }

    @GetMapping("updateTherapy")
    public JSONObject updateTherapy(@RequestParam Long id,
                                    @RequestParam String stringAnimalId,
                                    @RequestParam String stringWorkerId,
                                    @RequestParam String description,
                                    @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date startTime,
                                    @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date endTime) {
        JSONObject response = new JSONObject();
        if (StringUtil.isEmpty(stringAnimalId)) {
            response.put("code", 500);
            response.put("successfullyUpdate", false);
            response.put("message", "请选择就诊动物");
            return response;
        }
        Long animalId = Long.parseLong(stringAnimalId);

        if (StringUtil.isEmpty(stringWorkerId)) {
            response.put("code", 500);
            response.put("successfullyUpdate", false);
            response.put("message", "请选择治疗人员");
            return response;
        }
        Long workerId = Long.parseLong(stringWorkerId);

        if(startTime.compareTo(endTime) > 0){
            response.put("code", 500);
            response.put("successfullyUpdate", false);
            response.put("message", "开始时间不能晚于结束时间");
            return response;
        }

        therapyRepository.updateById(id, animalId, workerId,description, startTime, endTime);
        response.put("code", 200);
        response.put("successfullyUpdate", true);
        response.put("message", "治疗记录修改成功");
        return response;
    }

    @GetMapping("deleteTherapy")
    public JSONObject deleteTherapy(@RequestParam Long id) {
        JSONObject response = new JSONObject();
        therapyRepository.deleteById(id);
        response.put("code", 200);
        response.put("successfullyDelete", true);
        response.put("message", "治疗记录删除成功");
        return response;
    }
}
