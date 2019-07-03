package com.yidiandian.process;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/7/3 15:34
 * @Email: 15290810931@163.com
 */
@Slf4j
@Component
public class ArticleProcessor implements PageProcessor{
    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links()
                .regex("https://blog.csdn.net/[a-z 0-9 -] +/article/details/[0-9]{8}").all());
        String title= page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div[1]/h1/text()").get();
        String content= page.getHtml().xpath("//*[@id=\"article_content\"]/div/div[1]").get();
        log.info("获取的标题：{},内容：{}",title,content);

        if (StringUtils.isNoneBlank(title) && StringUtils.isNoneBlank(content)){
          page.putField("title",title);
          page.putField("content",content);
        }else{
            //跳过
            page.setSkip(true);
        }
    }

    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3000).setSleepTime(100);
    }
}
