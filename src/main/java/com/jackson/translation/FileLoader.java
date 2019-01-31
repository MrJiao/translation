package com.jackson.translation;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
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
        if(files==null){
            return null;
        }
        TreeMap<String, String> treeMap = new TreeMap<>();
        for (File file : files) {
            if(file==null)continue;
            L.d("加载资源:"+file.getName());
            loadString3(file,treeMap);
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
           /* string = string.replaceAll("\"","")
                    .replaceAll("\\(","（")
                    .replaceAll("\\)","）");*/
            String[] split = string.split(",");
            String word = "";
            if(split.length<1)continue;
            for(int i=0;i<split.length-1;i++){
                word = word+","+split[i].trim();
            }
            try {
                String chinese = split[split.length - 1].replaceAll(";", "")
                        .replaceAll("；", "").trim();
                treeMap.put(chinese,word.substring(1));
            }catch (Exception e){
                L.d(string);
                e.printStackTrace();
            }

        }
    }

    private void loadString3(File file, TreeMap<String, String> treeMap) throws IOException {
        InputStream is = new FileInputStream(file);
        XSSFWorkbook excel = new XSSFWorkbook(is);
        try {
            for (int numSheet = 0; numSheet < excel.getNumberOfSheets(); numSheet++) {
                XSSFSheet sheet = excel.getSheetAt(numSheet);
                int lastRowNum = sheet.getLastRowNum();
                for (int index=0; index<lastRowNum;index++) {
                    XSSFRow row = sheet.getRow(index);
                    if(row==null)continue;
                    XSSFCell english = row.getCell(0);
                    XSSFCell chinese = row.getCell(1);
                    if(english==null || chinese==null) continue;
                    String englishValue = english.getStringCellValue();
                    englishValue = englishValue.replaceAll("（","\\(").replaceAll("）","\\)");
                    String chineseValue = chinese.getStringCellValue();
                    chineseValue = chineseValue.replaceAll("（","\\(").replaceAll("）","\\)");
                    treeMap.put( chineseValue,englishValue);
                }
            }
        }catch (Exception e){
            L.d(file.getName());
            L.exception(e);
        }finally {
            is.close();
        }

    }


    private File[] getFiles(String runtimePath){
        File source = new File(runtimePath, "source");
        source =  FileFormat.format(source);
        File[] files = source.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return StringUtils.endsWith(name, ".xlsx");
            }
        });
        return files;
    }
}
