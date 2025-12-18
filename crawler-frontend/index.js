const express = require('express');
const axios = require('axios');
const scrapeWebsite = require('./scraper');
const path = require('path');

const app = express();
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(express.static('public')); // 存放 CSS 和 JS

const JAVA_BACKEND_URL = 'http://localhost:8080/api/data';

// 1. 首页：发送简单的 HTML 界面
app.get('/', (req, res) => {
    res.sendFile(path.join(__currentDirname, 'index.html'));
});

// 2. 爬取接口：接收前端 URL -> 运行 Puppeteer -> 发给 Java
app.post('/crawl', async (req, res) => {
    const { targetUrl } = req.body;
    try {
        console.log(`正在启动爬虫访问: ${targetUrl}`);
        const data = await scrapeWebsite(targetUrl);
        
        console.log(`爬取成功，正在发送到 Java 后端...`);
        // 调用我们之前写好的 Java Spring Boot 接口
        const response = await axios.post(JAVA_BACKEND_URL, data);
        
        res.json({ success: true, message: `成功爬取并存储 ${data.length} 条数据`, data: response.data });
    } catch (error) {
        console.error(error);
        res.status(500).json({ success: false, message: '爬取失败: ' + error.message });
    }
});

app.listen(3000, () => {
    console.log('Node.js 前端已启动: http://localhost:3000');
});