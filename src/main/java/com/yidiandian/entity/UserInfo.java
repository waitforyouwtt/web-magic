package com.yidiandian.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/7/3 17:44
 * @Email: 15290810931@163.com
 */
@Data
@Entity
@Table(name = "tb_userInfo")
public class UserInfo {
    @Id
    String id;
    String nickname;
    String avatar;

}
