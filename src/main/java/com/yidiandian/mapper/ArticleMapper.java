package com.yidiandian.mapper;

import com.yidiandian.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: luoxian
 * @Date: 2019/5/28 10:42
 */
public interface ArticleMapper extends JpaRepository<Article,String> {
}
