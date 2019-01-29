package com.jackson.translation;

import org.apache.commons.lang3.StringUtils;

/**
 * Create by: Jackson
 */
public class L {

    public static void d(String msg){
        System.out.println(msg);
    }

    public static void exception(Exception e) {
        e.printStackTrace();
    }

    public static void console(String message){
        if(StringUtils.isEmpty(message))return;
        StringBuilder sb = new StringBuilder();
        String title = "* * * * * * * * * * * * * * * * * * * * * * * * * * * *";
        sb.append(title).append(System.getProperty("line.separator"));
        int length = message.length();
        int bianju = (title.length() - length)/2;
        for(int i=0;i<bianju-1;i++){
            sb.append(" ");
        }
        sb.append(message);
        for(int i=0;i<bianju-1;i++){
            sb.append(" ");
        }
        sb.append(System.getProperty("line.separator"));
        sb.append(title);
        System.out.println(sb.toString());

    }
}
