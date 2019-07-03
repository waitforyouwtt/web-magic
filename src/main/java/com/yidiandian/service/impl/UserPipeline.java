package com.yidiandian.service.impl;

import com.yidiandian.entity.UserInfo;
import com.yidiandian.mapper.UserMapper;
import com.yidiandian.utils.DownloadUtil;
import com.yidiandian.utils.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.IOException;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/7/3 17:38
 * @Email: 15290810931@163.com
 */
@Slf4j
@Component
public class UserPipeline implements Pipeline {

    @Autowired
    private SnowflakeIdWorker idWorker;
    @Autowired
    private UserMapper userMapper;
    @Override
    public void process(ResultItems resultItems, Task task) {
        UserInfo user = new UserInfo();
        user.setId(idWorker.nextId() + "");
        user.setNickname(resultItems.get("nickname"));
        String image = resultItems.get("image");
        String fileName = image.substring(image.lastIndexOf("/")+1);
        user.setAvatar(fileName);
        userMapper.save(user);
        //下载图片
        try{
            DownloadUtil.download(image,fileName,"E:/userimg");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
