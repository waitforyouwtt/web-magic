package com.yidiandian.mapper;

import com.yidiandian.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/7/3 17:46
 * @Email: 15290810931@163.com
 */
public interface UserMapper extends JpaRepository<UserInfo,String> {
}
