package com.example.zookeeper.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.zookeeper.dao.Area;
import com.example.zookeeper.repository.AreaRepository;
import com.example.zookeeper.util.StringUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class AreaController {
    @Resource
    private AreaRepository areaRepository;

    @GetMapping("addArea")
    public JSONObject addArea(@RequestParam String areaName) {
        JSONObject response = new JSONObject();
        if (StringUtil.isEmpty(areaName)) {
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "区域名称不能为空");
            return response;
        }
        Area area = areaRepository.getByAreaName(areaName);
        if (area != null) {
            response.put("code", 400);
            response.put("successfullyAdd", false);
            response.put("message", "该区域已经存在");
            return response;
        }
        areaRepository.insert(areaName);
        response.put("code", 200);
        response.put("successfullyAdd", true);
        response.put("message", "区域新增成功");
        return response;
    }

    @GetMapping("getAllArea")
    public JSONObject getAllArea() {
        JSONObject response = new JSONObject();
        List<Area> areaList = areaRepository.getAllArea();
        response.put("code", 200);
        response.put("areaList", JSON.toJSON(areaList));
        response.put("message", "区域查询成功");
        return response;
    }

    @GetMapping("updateArea")
    public JSONObject updateArea(@RequestParam Long id, @RequestParam String newAreaName) {
        JSONObject response = new JSONObject();
        if (StringUtil.isEmpty(newAreaName)) {
            response.put("code", 500);
            response.put("successfullyUpdate", false);
            response.put("message", "传参缺失");
            return response;
        }
        Area area = areaRepository.getByAreaName(newAreaName);
        if (area != null) {
            response.put("code", 500);
            response.put("successfullyUpdate", false);
            response.put("message", "该区域已经存在");
            return response;
        }
        areaRepository.updateById(id, newAreaName);
        response.put("code", 200);
        response.put("successfullyUpdate", true);
        response.put("message", "区域修改成功");
        return response;
    }

    @GetMapping("deleteArea")
    public JSONObject deleteArea(@RequestParam Long id) {
        JSONObject response = new JSONObject();
        areaRepository.deleteById(id);
        response.put("code", 200);
        response.put("successfullyDelete", true);
        response.put("message", "区域删除成功");
        return response;
    }
}
