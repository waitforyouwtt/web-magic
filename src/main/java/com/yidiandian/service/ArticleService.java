package com.yidiandian.service;

import com.yidiandian.entity.Article;
import com.yidiandian.entity.Result;

/**
 * @Author: luoxian
 * @Date: 2019/5/28 10:39
 */
public interface ArticleService {

    Result<Article> save(Article article);

    /**
     * 修改审核状态
     * @param params
     * @return
     */
    Result<String> updateArticleState(Article params);
    /**
     * 点赞
     */
    Result<String> updateArticleThumbup(Article params);
    /**
     * 根据ID 从redis 获取文章,如果没有则从mysql 获取，并保存到redis
     */
    Result<Article> findByIdInRedis(String id);

    Article findById(String id);

}
