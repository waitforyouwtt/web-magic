package com.yidiandian.process;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/7/1 11:26
 * @Email: 15290810931@163.com
 */
public class MyProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        //System.out.println(page.getHtml().toString());

        //将当前页面里的所有链接都添加到目标页面中
        //page.addTargetRequests(page.getHtml().links().all());
        page.addTargetRequests(page.getHtml().links().regex("https://blog.csdn.net/[a‐z 0‐9‐]+/article/details/[0‐9]{8}").all() );System.out.println(page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div[1]/h1/text()").toString());
        System.out.println(page.getHtml().xpath("//*[@id =\"nav\"]/div/div/ul/li[5]/a").toString());

    }

    @Override
    public Site getSite() {
        return Site.me().setSleepTime(100).setRetryTimes(3);
    }

    public static void main(String[] args) {
      //  Spider.create(new MyProcessor()).addUrl("https://blog.csdn.net").run();
        //打印到控制台
        //Spider.create(new MyProcessor()).addUrl("https://blog.csdn.net").addPipeline(new ConsolePipeline()).run();
        //打印到控制台，且文件保存,设置内存队列
        Spider.create(new MyProcessor()).addUrl("https://blog.csdn.net")
                .addPipeline(new ConsolePipeline())
                .addPipeline(new FilePipeline("E:/data/json"))
                .addPipeline(new JsonFilePipeline("E:/json"))
                //.setScheduler(new QueueScheduler())
                //.setScheduler(new FileCacheQueueScheduler("E:\\data\\scheduler"))
                .setScheduler(new RedisScheduler("127.0.0.1"))
                .run();

    }
}
