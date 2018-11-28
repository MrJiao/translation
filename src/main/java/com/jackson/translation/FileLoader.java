package com.jackson.translation;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * Create by: Jackson
 */
public class FileLoader {

    public TreeMap<String,String> load() throws IOException {
        String runtimePath = new RuntimPath().getRuntimePath();
        L.d(runtimePath);
        File[] files = getFiles(runtimePath);
        TreeMap<String, String> treeMap = new TreeMap<>();
        for (File file : files) {
            L.d("加载资源:"+file.getName());
            loadString2(file,treeMap);
        }
        return treeMap;
    }

    private void loadString(File file, TreeMap<String, String> treeMap) throws IOException {
        List<String> strings = FileUtils.readLines(file, "gbk");
        int i=0;
        String pre="";
        for (String s : strings) {
            if(i%2!=0){
                treeMap.put(s,pre);
                i++;
                continue;
            }
            pre = s;
            i++;
        }
    }

    private void loadString2(File file, TreeMap<String, String> treeMap) throws IOException {
        List<String> strings = FileUtils.readLines(file, "gbk");
        for (String string : strings) {
            String[] split = string.split(",");
            String word = "";
            if(split.length<1)continue;
            for(int i=0;i<split.length-1;i++){
                word = word+","+split[i].replaceAll("\"","").trim();
            }
            try {
                treeMap.put(split[split.length-1],word.substring(1));
            }catch (Exception e){
                L.d(string);
                e.printStackTrace();
            }

        }
    }


    private File[] getFiles(String runtimePath){
        File source = new File(runtimePath, "source");
        source =  FileFormat.format(source);
        File[] files = source.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return StringUtils.endsWith(name, ".csv");
            }
        });
        return files;
    }
}
