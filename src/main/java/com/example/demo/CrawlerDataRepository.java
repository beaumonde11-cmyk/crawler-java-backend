package com.example.demo; // 确保使用您的实际包名

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawlerDataRepository extends JpaRepository<CrawlerData, Long> {
    // 继承 JpaRepository<实体类, 主键类型>
    // Spring Boot 会自动提供 save(), findAll(), findById() 等方法
}