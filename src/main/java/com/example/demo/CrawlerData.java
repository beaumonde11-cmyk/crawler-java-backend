package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class CrawlerData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 使用 TEXT 类型以确保能存下豆瓣或乐天的超长标题
    @Column(columnDefinition = "TEXT")
    private String title;

    // 同理，value 也设置为 TEXT 类型，防止评分或描述过长
    @Column(columnDefinition = "TEXT")
    private String value;

    // 无参数构造函数（JPA 必须）
    public CrawlerData() {
    }

    // 全参数构造函数（方便调试）
    public CrawlerData(String title, String value) {
        this.title = title;
        this.value = value;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}