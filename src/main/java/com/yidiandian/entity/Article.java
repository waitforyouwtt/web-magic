package com.yidiandian.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @Author: luoxian
 * @Date: 2019/5/28 10:31
 * 文章表
 */
@Entity
@Table(name = "tb_article")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    private String id;
    //专栏ID
    private String columnId;
    //用户ID
    private String userId;
    //文章标题
    private String title;
    //文章内容
    private String content;
    //文章封面
    private String image;
    //发布日期
    private LocalDateTime createTime;
    //修改日期
    private LocalDateTime updateTime;
    //是否公开
    private String isPublic;
    //是否置顶
    private String isTop;
    //浏览量
    private Integer visits;
    //点赞量
    private Integer thumbup;
    //评论数
    private Integer comment;
    //审核状态
    private String state;
    //所属频道
    private Integer channelId;
    //链接
    private String url;
    //文章类型
    private String type;
}
