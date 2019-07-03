package com.yidiandian.utils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/7/3 17:29
 * @Email: 15290810931@163.com
 */
public class DownloadUtil {
    public static void download(String urlStr,String filename,
                                String savePath) throws IOException {
        URL url = new URL(urlStr);
        //打开URL 连接
        URLConnection connection = url.openConnection();
        //请求超时时间
        connection.setConnectTimeout(5000);
        //输入流
        InputStream in = connection.getInputStream();
        //缓冲数据
        byte []  bytes = new byte[1024];
        //数据长度
        int len;
        //文件
        File file = new File(savePath);
        if (!file.exists()){
            file.mkdir();
            OutputStream out = new FileOutputStream(file.getPath() +"\\"+filename);
            //先读到bytes 中
            while((len = in.read(bytes)) != -1){
                //再从bytes 中写入文件
                out.write(bytes,0,len);
            }
            out.close();
            in.close();
        }

    }
}
