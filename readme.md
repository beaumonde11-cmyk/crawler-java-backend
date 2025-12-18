本项目是一个端到端的数据采集解决方案，实现了从 **网页自动化抓取** 到 **Java 后端存储** 再到 **Excel 报表生成** 的全流程。

---

## 🛠️ 技术架构

本系统采用解耦架构，确保了抓取逻辑与存储逻辑的分离：

```mermaid
graph TD
    A[/目标网站] -->|Puppeteer 爬取| B(Node.js 爬虫脚本)
    B -->|生成| C{本地 Excel 报表}
    B -->|REST API 推送| D[Spring Boot 后端]
    D -->|JPA 存储| E[(H2 内存数据库)]
    D -->|API 暴露| F[前端可视化界面]





