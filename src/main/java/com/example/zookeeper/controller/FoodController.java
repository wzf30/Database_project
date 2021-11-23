package com.example.zookeeper.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.zookeeper.dao.Food;
import com.example.zookeeper.po.FoodPO;
import com.example.zookeeper.repository.FoodRepository;
import com.example.zookeeper.util.StringUtil;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FoodController {
    @Resource
    private FoodRepository foodRepository;

    @GetMapping("getAllFood")
    public JSONObject getAllFood() {
        JSONObject response = new JSONObject();
        List<Food> foodList = foodRepository.getAllFood();
        List<FoodPO> foodPOList = foodList.stream().map(this::convertFoodToFoodPO).collect(Collectors.toList());
        response.put("code", 200);
        response.put("foodList", foodPOList);
        response.put("message", "食物查询成功");
        return response;
    }

    @GetMapping("getFoodByName")
    public JSONObject getFoodByName(@RequestParam String name) {
        JSONObject response = new JSONObject();
        Food food = foodRepository.getByName(name);
        response.put("code", 200);
        response.put("food", JSON.toJSON(food));
        response.put("message", "食物查询成功");
        return response;
    }

    @GetMapping("addFood")
    public JSONObject addFood(@RequestParam String name, @RequestParam String unit) {
        JSONObject response = new JSONObject();
        if (StringUtil.isEmpty(name)) {
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "食物名称不能为空");
            return response;
        }

        if (StringUtil.isEmpty(unit)) {
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "食物单位不能为空");
            return response;
        }

        Food food = foodRepository.getByName(name);
        if (food != null) {
            response.put("code", 400);
            response.put("successfullyAdd", false);
            response.put("message", "该种食物已经存在");
            return response;
        }
        foodRepository.insert(name, unit);
        response.put("code", 200);
        response.put("successfullyAdd", true);
        response.put("message", "食物添加成功");
        return response;
    }

    @GetMapping("updateFood")
    public JSONObject updateFood(@RequestParam Long id,
                                 @RequestParam String name,
                                 @RequestParam String unit,
                                 @RequestParam double storage) {
        JSONObject response = new JSONObject();
        if (StringUtil.isEmpty(name)) {
            response.put("code", 500);
            response.put("successfullyUpdate", false);
            response.put("message", "食物名称不能为空");
            return response;
        }

        if (StringUtil.isEmpty(unit)) {
            response.put("code", 500);
            response.put("successfullyUpdate", false);
            response.put("message", "食物单位不能为空");
            return response;
        }

        Food food = foodRepository.getByName(name);
        if (food != null && id != food.getId()) {
            response.put("code", 400);
            response.put("successfullyUpdate", false);
            response.put("message", "该种食物已经存在");
            return response;
        }
        foodRepository.updateById(id, name, unit, storage);
        response.put("code", 200);
        response.put("successfullyUpdate", true);
        response.put("message", "食物信息更新成功");
        return response;
    }

    @GetMapping("deleteFood")
    public JSONObject deleteFood(@RequestParam Long id) {
        JSONObject response = new JSONObject();
        foodRepository.deleteById(id);
        response.put("code", 200);
        response.put("successfullyDelete", true);
        response.put("message", "食物删除成功");
        return response;
    }

    private FoodPO convertFoodToFoodPO(Food food) {
        FoodPO po = new FoodPO();
        po.setId(food.getId());
        po.setName(food.getName());
        po.setUnit(food.getUnit());
        po.setStorage(String.valueOf(food.getStorage()));
        return po;
    }
}
