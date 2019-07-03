package com.yidiandian.task;

import com.yidiandian.process.ArticleProcessor;
import com.yidiandian.service.impl.ArticleDbPipeline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/7/3 16:13
 * @Email: 15290810931@163.com
 */
@Component
@Slf4j
public class ArticleTask {
    @Autowired
    private ArticleDbPipeline articleDbPipeline;
    @Autowired
    private ArticleTxtPipline articleTxtPipline;
    @Autowired
    private RedisScheduler redisScheduler;
    @Autowired
    private ArticleProcessor articleProcessor;

    /**
     * 爬取 ai 数据
     */
    /*@Scheduled(cron = "0 54 21 * * ?")*/
    @Scheduled(cron = "0 */2 * * * ?")
    public void aiTask(){
       log.info("爬取ai 文章");
       Spider spider = Spider.create(articleProcessor);
       spider.addUrl("https://blog.csdn.net/nav/ai");
       articleTxtPipline.setChannelId(521);
       articleDbPipeline.setChannelId(521);
       spider.addPipeline(articleDbPipeline);
      spider.addPipeline(articleTxtPipline);
       spider.setScheduler(redisScheduler);
       spider.start();
    }

    /**
     * 爬取 db 数据
     */
    //@Scheduled(cron = "20 17 11 * * ?")
    @Scheduled(cron = "0 */2 * * * ?")
    public void dbTask(){
        log.info(" db 文章");
        Spider spider = Spider.create(articleProcessor);
        spider.addUrl("https://blog.csdn.net/nav/db");
        articleTxtPipline.setChannelId(1314);
        spider.addPipeline(articleDbPipeline);
        spider.setScheduler(redisScheduler);
        spider.start();
    }

    /**
     * 爬取 web 数据
     */
   /* @Scheduled(cron = "20 27 11 * * ?")*/
    @Scheduled(cron = "0 */2 * * * ?")
    public void webTask(){
        log.info("爬取 web 文章");
        Spider spider = Spider.create(articleProcessor);
        spider.addUrl("https://blog.csdn.net/nav/web");
        articleTxtPipline.setChannelId(000);
        spider.addPipeline(articleDbPipeline);
        spider.setScheduler(redisScheduler);
        spider.start();
    }



}
