package com.example.zookeeper.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.zookeeper.dao.Animal;
import com.example.zookeeper.dao.FeedRecord;
import com.example.zookeeper.dao.Food;
import com.example.zookeeper.dao.Species;
import com.example.zookeeper.po.FeedRecordPO;
import com.example.zookeeper.repository.AnimalRepository;
import com.example.zookeeper.repository.FeedRecordRepository;
import com.example.zookeeper.repository.FoodRepository;
import com.example.zookeeper.repository.SpeciesRepository;
import com.example.zookeeper.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FeedController {
    @Resource
    private FoodRepository foodRepository;
    @Resource
    private AnimalRepository animalRepository;
    @Resource
    private FeedRecordRepository feedRecordRepository;
    @Resource
    private SpeciesRepository speciesRepository;

    @GetMapping("queryAllFeedRecord")
    public JSONObject queryAllFeedRecord() {
        JSONObject response = new JSONObject();
        List<FeedRecord> feedRecordList = feedRecordRepository.getAllFeedRecord();
        List<FeedRecordPO> feedRecordPOList = feedRecordList.stream().map(this::convertFeedRecordToFeedRecordPO).collect(Collectors.toList());
        response.put("code", 200);
        response.put("feedRecordList", feedRecordPOList);
        response.put("message", "喂养记录查询成功");
        return response;
    }

    @GetMapping("addFeedRecord")
    public JSONObject addFeedRecord(@RequestParam String foodName,
                                    @RequestParam Long animalId,
                                    @RequestParam double appetite,
                                    String date) {
        JSONObject response = new JSONObject();
        Food food = foodRepository.getByName(foodName);
        Animal animal = animalRepository.getById(animalId);
        if (food == null) {
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "食物不能为空");
            return response;
        }
        if (food.getStorage() < appetite) {
            response.put("code", 404);
            response.put("successfullyAdd", false);
            response.put("message", "食物不足，喂养失败");
            return response;
        }
        if (animal == null) {
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "动物不能为空");
            return response;
        }
        foodRepository.updateStorageById(food.getId(), food.getStorage() - appetite);
        feedRecordRepository.insert(food.getId(), animal.getId(), appetite, DateUtil.convertDateFromStr(date));
        response.put("code", 200);
        response.put("successfullyAdd", true);
        response.put("message", "喂养记录插入成功");
        return response;
    }

    @GetMapping("updateFeed")
    public JSONObject updateFeed(@RequestParam Long id,
                                 @RequestParam String stringAnimalId,
                                 @RequestParam String foodName,
                                 @RequestParam Double appetite,
                                 @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        JSONObject response = new JSONObject();
        Long animalId = Long.parseLong(stringAnimalId);
        Food food = foodRepository.getByName(foodName);
        Animal animal = animalRepository.getById(animalId);
        feedRecordRepository.updateById(id, food.getId(), animal.getId(), appetite, date);
        response.put("code", 200);
        response.put("successfullyUpdate", toString());
        response.put("message", "喂养记录修改成功");
        return response;
    }

    @GetMapping("deleteFeedRecord")
    public JSONObject deleteFeedRecord(@RequestParam Long id) {
        feedRecordRepository.deleteById(id);
        JSONObject response = new JSONObject();
        response.put("code", 200);
        response.put("successfullyDelete", toString());
        response.put("message", "喂养记录删除成功");
        return response;
    }

    private FeedRecordPO convertFeedRecordToFeedRecordPO(FeedRecord feedRecord) {
        Food food = foodRepository.getById(feedRecord.getFoodId());
        Animal animal = animalRepository.getById(feedRecord.getAnimalId());
        Species species = speciesRepository.getById(animal.getSpeciesId());
        FeedRecordPO po = new FeedRecordPO();
        po.setId(feedRecord.getId());
        po.setFoodName(food.getName());
        po.setAnimalId(animal.getId());
        po.setAnimalName(animal.getName());
        po.setSpecies(species.getName());
        po.setAppetite(feedRecord.getAppetite());
        po.setFeedTime(DateUtil.convertStrFromDate(feedRecord.getDate()));
        return po;
    }
}
