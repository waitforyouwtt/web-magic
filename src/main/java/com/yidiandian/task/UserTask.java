package com.yidiandian.task;

import com.yidiandian.process.UserProcessor;
import com.yidiandian.service.impl.UserPipeline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/7/3 17:47
 * @Email: 15290810931@163.com
 */
@Component
@Slf4j
public class UserTask {

    @Autowired
    private RedisScheduler redisScheduler;
    @Autowired
    private UserPipeline userPipeline;
    @Autowired
    private UserProcessor userProcessor;

    /**
     * 爬取用户数据
     */
    @Scheduled(cron = "0 */2 * * * ?")
    public void userTask(){
       log.info("----------爬取用户信息------------");
       Spider spider = Spider.create(userProcessor);
       spider.addUrl("https://blog.csdn.net");
       spider.addPipeline(userPipeline);
       spider.setScheduler(redisScheduler);
       spider.start();
    }

}
