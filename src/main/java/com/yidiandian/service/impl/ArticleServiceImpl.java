package com.yidiandian.service.impl;

import com.alibaba.fastjson.JSON;
import com.yidiandian.entity.Article;
import com.yidiandian.entity.Result;
import com.yidiandian.entity.ResultEnum;
import com.yidiandian.mapper.ArticleMapper;
import com.yidiandian.service.ArticleService;
import com.yidiandian.utils.MyException;
import com.yidiandian.utils.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @Author: luoxian
 * @Date: 2019/5/28 10:41
 */
@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public Result<Article> save(Article article) {
        article.setId(snowflakeIdWorker.nextId()+"");
        return Result.success(articleMapper.save(article));
    }

    @Override
    public Result<String> updateArticleState(Article params) {
        Optional<Article> optional = articleMapper.findById(params.getId());
        if (!optional.isPresent()){
            throw new MyException(ResultEnum.RECORD_NON_EXISTENT.getCode(), ResultEnum.RECORD_NON_EXISTENT.getMsg());
        }else{
            Article param = optional.get();
            param.setState(params.getState());
            redisTemplate.delete("article_".concat(params.getId()));
            articleMapper.save(param);
        }
        return Result.success("修改成功");
    }

    @Override
    public Result<String> updateArticleThumbup(Article params) {
        Optional<Article> optional = articleMapper.findById(params.getId());
        if (!optional.isPresent()){
            throw new MyException(ResultEnum.RECORD_NON_EXISTENT.getCode(), ResultEnum.RECORD_NON_EXISTENT.getMsg());
        }else{
            Article param = optional.get();
            int thumbup ;
            if (param.getThumbup() == null){
                thumbup = 0;
            }else{
                thumbup = param.getThumbup();
            }
            log.info("点赞量为：{}",thumbup);
            param.setThumbup(thumbup+1);
            redisTemplate.delete("article_".concat(params.getId()));
            articleMapper.save(param);
        }
        return Result.success("点赞成功");
    }
    @Override
    public Result<Article> findByIdInRedis(String id) {
        //从缓存中获取内容
        Object object = redisTemplate.opsForValue().get("article_".concat(id));
        Article article = null;
        if (object != null){
            article = JSON.parseObject(object.toString(),Article.class);
        }
        if (article == null){
            article = findById(id);
            //redisTemplate.opsForValue().set("article_".concat(id), JSON.toJSONString(article));
            //缓存一天时间
            redisTemplate.opsForValue().set("article_".concat(id), JSON.toJSONString(article),1, TimeUnit.DAYS);
        }
        return Result.success(article);
    }

    @Cacheable(value = "Article",key = "id")
    @Override
    public Article findById(String id) {
        Optional<Article> optional = articleMapper.findById(id);
        if (!optional.isPresent()){
            throw new MyException(ResultEnum.RECORD_NON_EXISTENT.getCode(), ResultEnum.RECORD_NON_EXISTENT.getMsg());
        }else{
            return  optional.get();
        }
    }
}
