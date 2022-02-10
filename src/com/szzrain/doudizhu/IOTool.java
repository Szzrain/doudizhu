package com.szzrain.doudizhu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 读取与写入JSON文件
 * */

public class IOTool {


    public static void saveJson(String jsonDiagram, String filePath){
        String writeString = JSON.toJSONString(jsonDiagram, SerializerFeature.PrettyFormat);


        BufferedWriter writer = null;
        File file = new File(filePath);
        //如果文件不存在则新建
        if (!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException e){
                System.out.println(e);
            }
        }
        //如果多次执行同一个流程，会导致json文件内容不断追加，在写入之前清空文件
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false),"UTF-8"));
            writer.write("");
            writer.write(writeString);
        }catch (IOException e){
            System.out.println(e);
        }finally {
            try{
                if (writer != null){
                    writer.close();
                }
            }catch (IOException e){
                System.out.println(e);
            }
        }
    }

    // 用于读取JSON文件
    public static String readJsonFile(String filePath){
        BufferedReader reader = null;
        StringBuilder readJson = new StringBuilder();
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null){
                readJson.append(tempString);
            }
        }catch (IOException e){
            System.out.println(e);
        }finally {
            if (reader != null){
                try {
                    reader.close();
                }catch (IOException e){
                    System.out.println(e);
                }
            }
        }


        return readJson.toString();

    }

}