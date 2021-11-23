package com.example.zookeeper.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.zookeeper.dao.Area;
import com.example.zookeeper.dao.District;
import com.example.zookeeper.dao.Team;
import com.example.zookeeper.dao.Worker;
import com.example.zookeeper.po.DistrictPO;
import com.example.zookeeper.po.WorkerPO;
import com.example.zookeeper.repository.TeamRepository;
import com.example.zookeeper.repository.WorkerRepository;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


import com.example.zookeeper.util.StringUtil;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class WorkerController {
    @Resource
    private WorkerRepository workerRepository;

    @Resource
    private TeamRepository teamRepository;

    private List<WorkerPO> convertWorkerListToWorkerPOList(List<Worker> workerList) {
        List<WorkerPO> workerPOList = new ArrayList<>();
        for (Worker worker : workerList) {
            Team team = teamRepository.getById(worker.getTeamId());
            WorkerPO workerPO = new WorkerPO();
            workerPO.setId(worker.getId());
            workerPO.setName(worker.getName());
            workerPO.setAge(worker.getAge());
            workerPO.setGender(worker.getGender());
            workerPO.setWorkingAge(worker.getWorkingAge());
            workerPO.setTeamName(team.getName());
            workerPO.setExperienced(worker.getExperienced());
            workerPO.setVet(worker.getVet());
            workerPOList.add(workerPO);
        }
        return workerPOList;
    }

    @GetMapping("addWorker")
    public JSONObject addWorker(@RequestParam String name,
                                @RequestParam Long age,
                                @RequestParam String gender,
                                @RequestParam Long workingAge,
                                @RequestParam String teamName,
                                @RequestParam Boolean experienced,
                                @RequestParam Boolean vet) {
        JSONObject response = new JSONObject();
        if (StringUtil.isEmpty(name)) {
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "姓名不能为空");
            return response;
        }

        if (age < 0) {
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "不合法年龄");
            return response;
        }

        if (StringUtil.isEmpty(gender)) {
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "请选择性别");
            return response;
        }

        if (workingAge >= age) {
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "不合法工龄");
            return response;
        }
        Team team = teamRepository.getByName(teamName);
        workerRepository.insert(name, age, gender, workingAge, team.getId(), experienced, vet);
        response.put("code", 200);
        response.put("successfullyAdd", true);
        response.put("message", "人员新增成功");
        return response;
    }

    @GetMapping("getAllWorker")
    public JSONObject getAllWorker() {
        JSONObject response = new JSONObject();
        List<Worker> workerList = workerRepository.getAllWorker();
        List<WorkerPO> workerPOList = convertWorkerListToWorkerPOList(workerList);
        response.put("code", 200);
        response.put("workerList", JSON.toJSON(workerPOList));
        response.put("message", "人员查询成功");
        return response;
    }

    @GetMapping("updateWorker")
    public  JSONObject updateWorker(@RequestParam Long id,
                                    @RequestParam String name,
                                    @RequestParam Long age,
                                    @RequestParam String gender,
                                    @RequestParam Long workingAge,
                                    @RequestParam String teamName,
                                    @RequestParam Boolean experienced,
                                    @RequestParam Boolean vet) {
        JSONObject response = new JSONObject();
        if (StringUtil.isEmpty(name)) {
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "姓名不能为空");
            return response;
        }

        if (age < 0) {
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "不合法年龄");
            return response;
        }

        if (StringUtil.isEmpty(gender)) {
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "请选择性别");
            return response;
        }

        if (workingAge >= age) {
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "不合法工龄");
            return response;
        }

        Team team = teamRepository.getByName(teamName);
        workerRepository.updateById(id, name, age, gender, workingAge, team.getId(), experienced, vet);
        response.put("code", 200);
        response.put("successfullyUpdate", true);
        response.put("message", "人员信息修改成功");
        return response;
    }

    @GetMapping("deleteWorker")
    public JSONObject deleteWorker(@RequestParam Long id) {
        JSONObject response = new JSONObject();
        workerRepository.deleteById(id);
        response.put("code", 200);
        response.put("successfullyDelete", true);
        response.put("message", "区域删除成功");
        return response;
    }
}
