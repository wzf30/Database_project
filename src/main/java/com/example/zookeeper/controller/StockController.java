package com.example.zookeeper.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.zookeeper.dao.Food;
import com.example.zookeeper.dao.StockRecord;
import com.example.zookeeper.po.StockRecordPO;
import com.example.zookeeper.repository.FoodRepository;
import com.example.zookeeper.repository.StockRecordRepository;
import com.example.zookeeper.util.DateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StockController {
    @Resource
    private StockRecordRepository stockRecordRepository;
    @Resource
    private FoodRepository foodRepository;

    @GetMapping("queryAllStockRecord")
    public JSONObject queryAllStockRecord() {
        JSONObject response = new JSONObject();
        List<StockRecord> stockRecordList = stockRecordRepository.getAllStockRecord();
        List<StockRecordPO> stockRecordPOList = stockRecordList.stream().map(this::convertStockRecordToStockRecordPO).collect(Collectors.toList());
        response.put("code", 200);
        response.put("stockRecordList", stockRecordPOList);
        response.put("message", "进货记录查询成功");
        return response;
    }

    @GetMapping("addStockRecord")
    public JSONObject addStockRecord(@RequestParam String foodName, @RequestParam double amount, @RequestParam Long cost, @RequestParam String time, @RequestParam String expireTime) {
        Food food = foodRepository.getByName(foodName);
        JSONObject response = new JSONObject();
        if (food == null) {
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "食物不能为空");
            return response;
        }
        Date stockDate = DateUtil.convertDateFromStr(time);
        Date expireDate = DateUtil.convertDateFromStr(expireTime);
        if (stockDate == null || expireDate == null || stockDate.getTime() >= expireDate.getTime()) {
            response.put("code", 500);
            response.put("successfullyAdd", false);
            response.put("message", "食物已过期，系统不予记录");
            return response;
        }
        stockRecordRepository.addStockRecord(food.getId(), amount, cost, stockDate, expireDate);
        foodRepository.updateStorageById(food.getId(), food.getStorage() + amount);
        response.put("code", 200);
        response.put("successfullyAdd", true);
        response.put("message", "进货记录添加成功");
        return response;
    }

    private StockRecordPO convertStockRecordToStockRecordPO(StockRecord stockRecord) {
        Food food = foodRepository.getById(stockRecord.getFoodId());
        StockRecordPO po = new StockRecordPO();
        po.setId(stockRecord.getId());
        po.setFoodId(food.getId());
        po.setFoodName(food.getName());
        po.setAmount(stockRecord.getAmount());
        po.setCost(stockRecord.getCost());
        po.setTime(DateUtil.convertStrFromDate(stockRecord.getTime()));
        po.setExpireTime(DateUtil.convertStrFromDate(stockRecord.getExpireTime()));
        return po;
    }

}
