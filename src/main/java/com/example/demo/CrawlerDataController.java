package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/data")
@CrossOrigin(origins = "*")
public class CrawlerDataController {

    @Autowired
    private CrawlerDataRepository repository;

    @PostMapping
    public List<CrawlerData> saveAll(@RequestBody List<CrawlerData> data) {
        return repository.saveAll(data);
    }

    @GetMapping
    public List<CrawlerData> getAll() {
        return repository.findAll();
    }
}