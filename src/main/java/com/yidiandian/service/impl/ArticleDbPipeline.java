package com.yidiandian.service.impl;

import com.yidiandian.entity.Article;
import com.yidiandian.mapper.ArticleMapper;
import com.yidiandian.utils.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/7/3 16:00
 * @Email: 15290810931@163.com
 */
@Component
@Slf4j
public class ArticleDbPipeline implements Pipeline {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    private SnowflakeIdWorker idWorker;

    public void setChannelId(Integer channelId){
        this.channelId = channelId;
    }
    //频道ID
    private Integer channelId;

    @Override
    public void process(ResultItems resultItems, Task task) {
       String title = resultItems.get("title");
       String content = resultItems.get("content");
       log.info("获取的标题：{},内容：{}",title,content);
       Article article = new Article();
       article.setId(idWorker.nextId()+"");
       article.setChannelId(channelId);
       article.setTitle(title);
       article.setContent(content);
       articleMapper.save(article);
    }
}
