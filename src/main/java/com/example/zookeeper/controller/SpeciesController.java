package com.example.zookeeper.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.zookeeper.dao.Area;
import com.example.zookeeper.dao.Species;
import com.example.zookeeper.repository.SpeciesRepository;
import com.example.zookeeper.util.StringUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SpeciesController {
    @Resource
    private SpeciesRepository speciesRepository;

    @GetMapping("addSpecies")
    public JSONObject addSpecies(@RequestParam String name,
                                 @RequestParam Long life,
                                 @RequestParam String level,
                                 @RequestParam String habit,
                                 @RequestParam String habitat) {
        JSONObject response = new JSONObject();
        if (StringUtil.isEmpty(name)) {
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "名称不能为空");
            return response;
        }
        Species species = speciesRepository.getByName(name);
        if (species != null) {
            response.put("code", 400);
            response.put("successfullyAdd", false);
            response.put("message", "该物种已经存在");
            return response;
        }
        if (life < 0) {
            response.put("code", 400);
            response.put("successfullyAdd", false);
            response.put("message", "不合法值");
            return response;
        }
        speciesRepository.insert(name, life, level, habit, habitat);
        response.put("code", 200);
        response.put("successfullyAdd", true);
        response.put("message", "物种新增成功");
        return response;
    }

    @GetMapping("updateSpecies")
    public JSONObject updateSpecies(@RequestParam Long id,
                                    @RequestParam String name,
                                    @RequestParam Long life,
                                    @RequestParam String level,
                                    @RequestParam String habit,
                                    @RequestParam String habitat) {
        JSONObject response = new JSONObject();
        if (StringUtil.isEmpty(name)) {
            response.put("code", 500);
            response.put("successfullyUpdate", false);
            response.put("message", "名称不能为空");
            return response;
        }
        Species species = speciesRepository.getByName(name);
        if (species != null && species.getId() != id) {
            response.put("code", 400);
            response.put("successfullyUpdate", false);
            response.put("message", "该物种已经存在");
            return response;
        }
        if (life < 0) {
            response.put("code", 400);
            response.put("successfullyUpdate", false);
            response.put("message", "不合法值");
            return response;
        }
        speciesRepository.updateById(id, name, life, level, habit, habitat);
        response.put("code", 200);
        response.put("successfullyUpdate", true);
        response.put("message", "物种信息修改成功");
        return response;
    }

    @GetMapping("getAllSpecies")
    public JSONObject getAllSpecies() {
        JSONObject response = new JSONObject();
        List<Species> speciesList = speciesRepository.getAllSpecies();
        response.put("code", 200);
        response.put("speciesList", JSON.toJSON(speciesList));
        response.put("message", "区域查询成功");
        return response;
    }

    @GetMapping("deleteSpecies")
    public JSONObject deleteSpecies(@RequestParam Long id) {
        JSONObject response = new JSONObject();
        speciesRepository.deleteById(id);
        response.put("code", 200);
        response.put("successfullyDelete", true);
        response.put("message", "区域删除成功");
        return response;
    }
}
