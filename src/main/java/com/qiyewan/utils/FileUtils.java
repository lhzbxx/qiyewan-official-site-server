package com.qiyewan.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by atom on 2016/11/7.
 */
public class FileUtils {
    private FileUtils(){}

    public static void writeString2File(String fileName, String content){
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(fileName)))) {
            writer.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getWebRootPath(HttpServletRequest request){
        return request.getSession().getServletContext().getRealPath("/").replace("\\", "/");
    }

    public static String getResourcesRootPath(){
        return FileUtils.class.getClassLoader().getResource("application.yaml").getPath().replace("application.yaml", "");
    }

//    public static void main(String[] args) {
//        System.out.println(getResourcesRootPath());
//        writeString2File(getResourcesRootPath() + "cache/bug.txt", "hello");
//    }
}