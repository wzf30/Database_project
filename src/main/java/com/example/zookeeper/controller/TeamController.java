package com.example.zookeeper.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.zookeeper.dao.Area;
import com.example.zookeeper.dao.District;
import com.example.zookeeper.dao.Team;
import com.example.zookeeper.dao.Worker;
import com.example.zookeeper.po.DistrictPO;
import com.example.zookeeper.po.TeamPO;
import com.example.zookeeper.repository.WorkerRepository;
import com.example.zookeeper.repository.TeamRepository;
import com.example.zookeeper.repository.AreaRepository;

import com.example.zookeeper.util.StringUtil;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TeamController {
    @Resource
    private TeamRepository teamRepository;
    @Resource
    private AreaRepository areaRepository;
    @Resource
    WorkerRepository workerRepository;

    private List<TeamPO> convertTeamListToTeamPOList(List<Team> teamList) {
        List<TeamPO> teamPOList = new ArrayList<>();
        for (Team team : teamList) {
            Area area = areaRepository.getById(team.getAreaId());
            Worker worker = workerRepository.getById(team.getCaptionId());
            TeamPO teamPO = new TeamPO();
            teamPO.setId(team.getId());
            teamPO.setName(team.getName());
            teamPO.setCaptionId(worker.getId());
            teamPO.setCaptionName(worker.getName());
            teamPO.setAreaName(area.getAreaName());
            teamPOList.add(teamPO);
        }
        return teamPOList;
    }

    @GetMapping("getAllTeam")
    public JSONObject getAllTeam() {
        JSONObject response = new JSONObject();
        List<Team> teamList = teamRepository.getAllTeam();
        List<TeamPO> teamPOList = convertTeamListToTeamPOList(teamList);
        response.put("code", 200);
        response.put("teamList", JSON.toJSON(teamPOList));
        response.put("message", "饲养队查询成功");
        return response;
    }

    @GetMapping("addTeam")
    public JSONObject addTeam(@RequestParam String name,
                              @RequestParam Long captionId,
                              @RequestParam String areaName) {
        JSONObject response = new JSONObject();
        if (StringUtil.isEmpty(name)) {
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "饲养队名称不能为空");
            return response;
        }

        Team team = teamRepository.getByName(name);
        if (team != null) {
            response.put("code", 400);
            response.put("successfullyAdd", false);
            response.put("message", "该名称已经存在");
            return response;
        }

        Area area = areaRepository.getByAreaName(areaName);
        Worker worker = workerRepository.getById(captionId);
        teamRepository.insert(name, worker.getId(), area.getId());
        response.put("code", 200);
        response.put("successfullyAdd", true);
        response.put("message", "饲养队添加成功");
        return response;
    }

    @GetMapping("updateTeam")
    public JSONObject updateTeam(@RequestParam long id,
                                 @RequestParam String name,
                                 @RequestParam Long captionId,
                                 @RequestParam String areaName) {
        JSONObject response = new JSONObject();
        if (StringUtil.isEmpty(name)) {
            response.put("code", 500);
            response.put("successfullyUpdate", false);
            response.put("message", "饲养队名称不能为空");
            return response;
        }
        Team team = teamRepository.getByName(name);
        if (team != null && team.getId() != id) {
            response.put("code", 400);
            response.put("successfullyUpdate", false);
            response.put("message", "该名称已经存在");
            return response;
        }
        Area area = areaRepository.getByAreaName(areaName);
        Worker worker = workerRepository.getById(captionId);
        teamRepository.updateById(id, name, worker.getId(), area.getId());
        response.put("code", 200);
        response.put("successfullyUpdate", true);
        response.put("message", "饲养队信息编辑成功");
        return response;
    }

    @GetMapping("deleteTeam")
    public JSONObject deleteDistrict(@RequestParam Long id) {
        JSONObject response = new JSONObject();
        teamRepository.deleteById(id);
        response.put("code", 200);
        response.put("successfullyDelete", true);
        response.put("message", "饲养队删除成功");
        return response;
    }
}
