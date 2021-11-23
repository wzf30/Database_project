package com.example.zookeeper.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.zookeeper.dao.Area;
import com.example.zookeeper.dao.District;
import com.example.zookeeper.po.DistrictPO;
import com.example.zookeeper.repository.AreaRepository;
import com.example.zookeeper.repository.DistrictRepository;
import com.example.zookeeper.util.StringUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DistrictController {
    @Resource
    private DistrictRepository districtRepository;
    @Resource
    private AreaRepository areaRepository;

    @GetMapping("addDistrict")
    public JSONObject addDistrict(@RequestParam String name, @RequestParam String areaName) {
        JSONObject response = new JSONObject();
        if (StringUtil.isEmpty(name)) {
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "居住区名称不能为空");
            return response;
        }

        District district = districtRepository.getByName(name);
        if (district != null) {
            response.put("code", 400);
            response.put("successfullyAdd", false);
            response.put("message", "该居住区已经存在");
            return response;
        }

        Area area = areaRepository.getByAreaName(areaName);
        districtRepository.insert(name, area.getId());
        response.put("code", 200);
        response.put("successfullyAdd", true);
        response.put("message", "居住区添加成功");
        return response;
    }

    @GetMapping("updateDistrict")
    public JSONObject updateDistrict(@RequestParam Long id,
                                     @RequestParam String name,
                                     @RequestParam String areaName) {
        JSONObject response = new JSONObject();
        if (StringUtil.isEmpty(name)) {
            response.put("code", 500);
            response.put("successfullyUpdate", false);
            response.put("message", "居住区名称不能为空");
            return response;
        }

        District district = districtRepository.getByName(name);
        if (district != null && district.getId() != id) {
            response.put("code", 400);
            response.put("successfullyUpdate", false);
            response.put("message", "该居住区已经存在");
            return response;
        }

        Area area = areaRepository.getByAreaName(areaName);
        districtRepository.updateById(id, name, area.getId());
        response.put("code", 200);
        response.put("successfullyUpdate", true);
        response.put("message", "居住区信息编辑成功");
        return response;
    }

    @GetMapping("getAllDistrict")
    public JSONObject getAllDistrict() {
        JSONObject response = new JSONObject();
        List<District> districtList = districtRepository.getAllDistrict();
        List<DistrictPO> districtPOList = convertDistrictListToDistrictPOList(districtList);
        response.put("code", 200);
        response.put("districtList", JSON.toJSON(districtPOList));
        response.put("message", "居住区查询成功");
        return response;
    }

    private List<DistrictPO> convertDistrictListToDistrictPOList(List<District> districtList) {
        List<DistrictPO> districtPOList = new ArrayList<>();
        for (District district : districtList) {
            Area area = areaRepository.getById(district.getAreaId());
            DistrictPO districtPO = new DistrictPO();
            districtPO.setId(district.getId());
            districtPO.setName(district.getName());
            districtPO.setAreaName(area.getAreaName());
            districtPOList.add(districtPO);
        }
        return districtPOList;
    }

    @GetMapping("deleteDistrict")
    public JSONObject deleteDistrict(@RequestParam Long id) {
        JSONObject response = new JSONObject();
        districtRepository.deleteById(id);
        response.put("code", 200);
        response.put("successfullyDelete", true);
        response.put("message", "居住区删除成功");
        return response;
    }
}
