package com.yidiandian.process;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/7/3 17:27
 * @Email: 15290810931@163.com
 */
@Component
public class UserProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("https://blog.csdn.net/[a‐z 0‐9 ‐]+/article/details/[0‐9]{8}").all());
        String nickname= page.getHtml().xpath("//*[@id=\"uid\"]/text()").get();
        String image= page.getHtml().xpath("//*[@id=\"asideProfile\"]/div[1]/div[1]/a").css("img","src").toString();
        if(nickname!=null && image!=null){ //如果有昵称和头像
            page.putField("nickname",nickname);
            page.putField("image",image);
        }else{
            page.setSkip(true);//跳过
        }
    }

    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3000).setSleepTime(100);
    }
}
