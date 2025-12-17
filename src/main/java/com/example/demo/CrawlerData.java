package com.example.demo; // 确保使用您的实际包名

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CrawlerData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 数据库主键

    private String productId;       // 产品 ID
    private String productName;     // 商品名称
    private String sellingPrice;    // 售价
    private String commentsJson;    // 10个以内的评论，存储为 JSON 字符串
    private String sourceUrl;       // 数据来源 URL

    // 默认构造函数是 JPA 要求的
    public CrawlerData() {}

    // Getter 和 Setter 方法 (JPA 推荐)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getSellingPrice() { return sellingPrice; }
    public void setSellingPrice(String sellingPrice) { this.sellingPrice = sellingPrice; }
    public String getCommentsJson() { return commentsJson; }
    public void setCommentsJson(String commentsJson) { this.commentsJson = commentsJson; }
    public String getSourceUrl() { return sourceUrl; }
    public void setSourceUrl(String sourceUrl) { this.sourceUrl = sourceUrl; }
}