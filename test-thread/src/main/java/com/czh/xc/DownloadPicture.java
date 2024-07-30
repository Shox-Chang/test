package com.czh.xc;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Author:CZH
 * Date:2024-05-25
 * Description:使用多线程下载图片
 */
public class DownloadPicture implements Runnable {

    public String url;
    public String name;

    public DownloadPicture(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownload webDownload = new WebDownload();
        webDownload.download(url, name);
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new DownloadPicture("http://lz.sinaimg.cn/large/8a65eec0gy1hnu2yvkycdj207i0alwij.jpg", "1.jpg"));
        Thread t2 = new Thread(new DownloadPicture("http://lz.sinaimg.cn/large/8a65eec0gy1hnu2wwikuwj207i0aljsx.jpg", "2.jpg"));
        Thread t3 = new Thread(new DownloadPicture("https://lz.sinaimg.cn/large/008w3CKjgy1hny00210cwj307i0algmq.jpg", "3.jpg"));

        t1.start();
        t2.start();
        t3.start();

    }

}

class WebDownload {
    public void download(String url, String name){
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，WebDownload出现问题");
        }
    }
}
