package com.yidiandian.task;

import com.yidiandian.entity.Article;
import com.yidiandian.mapper.ArticleMapper;
import com.yidiandian.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/7/3 16:17
 * @Email: 15290810931@163.com
 */
@Component
public class ArticleTxtPipline implements Pipeline {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    private SnowflakeIdWorker idWorker;

    private Integer channelId;

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        String title = resultItems.get("title");
        String content = resultItems.get("content");
        Article article = new Article();
        article.setId(idWorker.nextId()+"");
        article.setChannelId(channelId);
        article.setTitle(title);
        article.setContent(content);
        articleMapper.save(article);
    }
}
