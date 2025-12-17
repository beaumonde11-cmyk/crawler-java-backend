package com.example.demo; // 确保使用您的实际包名

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 声明这是一个 RESTful Controller
@RequestMapping("/api/data") // 基础路由
public class CrawlerDataController {

    @Autowired // 自动注入 Repository 接口实例
    private CrawlerDataRepository repository;

    // 1. 接收爬虫数据的 POST 接口
    @PostMapping
    public ResponseEntity<List<CrawlerData>> saveCrawlerData(@RequestBody List<CrawlerData> dataList) {
        // 使用 Repository 的 saveAll 批量保存数据
        List<CrawlerData> savedData = repository.saveAll(dataList);
        // 返回保存成功的数据列表和 HTTP 200 OK 状态
        return ResponseEntity.ok(savedData);
    }

    // 2. 查询所有数据的 GET 接口
    @GetMapping
    public List<CrawlerData> getAllData() {
        return repository.findAll();
    }
}