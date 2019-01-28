package com.jackson.translation;

import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingQueue;

import static com.jackson.translation.Config.isDebug;

/**
 * Create by: Jackson
 */
public class Task extends Thread {

    public String preTranslate="";

    @Override
    public void run() {
        try{

            LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
            //注册热键
            if(!isDebug){
                new HotKey().init(new MyKey(queue));
            }

            L.console("正在加载中...");
            FileLoader fileLoader = new FileLoader();
            TreeMap<String, String> map = fileLoader.load();
            if(map==null || map.size()==0){
                L.d("加载失败");
                Thread.sleep(5000);
                return;
            }
            ClipboardComp clipboardComp = new ClipboardComp();
            L.d("启动成功");
            L.d("选中文本后按 ctrl + p 翻译  按 ctrl+q 关闭");
            while (true){
                //睡
                if(!isDebug)
                    queue.take();
                //ctrlX();
                String input = clipboardComp.getClipboardString();
                if (StringUtils.isEmpty(input))continue;
                if(StringUtils.equals(preTranslate,input))continue;
                input = input.replaceAll("（","\\(").replaceAll("）","\\)").trim();;
                L.d(input);
                String translate = translate(map,input);
                L.d(translate);
                clipboardComp.setClipboardString(translate);
                preTranslate = translate;
                //ctrlV();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void ctrlV() throws AWTException, InterruptedException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(100);
    }

    private void ctrlX() throws AWTException, InterruptedException {
        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_X);
        robot.keyRelease(KeyEvent.VK_X);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(100);
    }


    public static String translate(TreeMap<String, String> map, String input){
        if(StringUtils.isEmpty(input))return null;
        String[] split = input.split("；");
        String fanyi="";
        for (String s : split) {
            s = s.trim();
            fanyi = fanyi+";"+get(map,s);
        }
        if(fanyi.length()>1)
            fanyi = fanyi.substring(1);
        return fanyi;
    }

    public static String get(TreeMap<String,String> map,String input){
        return map.get(input);
    }

}
