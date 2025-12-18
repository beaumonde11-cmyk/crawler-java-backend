const puppeteer = require('puppeteer');
const axios = require('axios');
const XLSX = require('xlsx'); // 引入 Excel 库

(async () => {
    const browser = await puppeteer.launch({ headless: "new", args: ['--no-sandbox'] });
    const page = await browser.newPage();
    await page.setUserAgent('Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36');

    console.log('🔗 正在抓取数据并准备导出 Excel...');
    try {
        await page.goto('https://movie.douban.com/chart', { waitUntil: 'networkidle2' });

        const data = await page.evaluate(() => {
            const items = document.querySelectorAll('.item');
            return Array.from(items).slice(0, 15).map(item => ({
                电影名称: item.querySelector('.pl2 a')?.innerText.replace(/\s+/g, ' ').trim(),
                评分数据: item.querySelector('.rating_nums')?.innerText
            })).filter(i => i.电影名称);
        });

        if (data.length > 0) {
            // --- 核心逻辑：存入 Excel ---
            const worksheet = XLSX.utils.json_to_sheet(data);
            const workbook = XLSX.utils.book_new();
            XLSX.utils.book_append_sheet(workbook, worksheet, "豆瓣数据");
            
            // 生成文件名：豆瓣电影_时间戳.xlsx
            const fileName = `豆瓣电影数据_${Date.now()}.xlsx`;
            XLSX.writeFile(workbook, fileName);
            console.log(`📊 本地 Excel 文件已生成: ${fileName}`);

            // --- 同步到 Java 后端 (可选) ---
            // 将 key 还原为英文以适配 Java 实体类
            const javaData = data.map(item => ({ title: item.电影名称, value: item.评分数据 }));
            await axios.post('http://localhost:8080/api/data', javaData);
            console.log('🚀 数据同步至数据库成功！');
        }

    } catch (err) {
        console.error('❌ 出错:', err.message);
    } finally {
        await browser.close();
        console.log('🏁 任务结束。');
    }
})();