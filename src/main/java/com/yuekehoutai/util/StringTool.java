package com.yuekehoutai.util;

public class StringTool {
    //删除重复字符串
    public static String trimStr(String str, String indexStr){
        if(str == null){
            return null;
        }
        StringBuilder newStr = new StringBuilder(str);
        if(newStr.indexOf(indexStr) == 0){
            newStr = new StringBuilder(newStr.substring(indexStr.length()));//在开头

        }else if(newStr.indexOf(indexStr) == newStr.length() - indexStr.length()){
            newStr = new StringBuilder(newStr.substring(0,newStr.lastIndexOf(indexStr)));//在结尾

        }else if(newStr.indexOf(indexStr) < (newStr.length() - indexStr.length())){
            newStr =  new StringBuilder(newStr.substring(0,newStr.indexOf(indexStr))//在中间
                    +newStr.substring(newStr.indexOf(indexStr)+indexStr.length(),newStr.length()));

        }
        return newStr.toString();
    }
    //删除图片并去掉逗号
    public static String trimImage(String str, String indexStr){
        if(str == null){
            return null;
        }
        StringBuilder newStr = new StringBuilder(str);
        if(newStr.indexOf(indexStr) == 0){
            newStr = new StringBuilder(newStr.substring(indexStr.length()));//在开头

        }else if(newStr.indexOf(indexStr) == newStr.length() - indexStr.length()){
            newStr = new StringBuilder(newStr.substring(0,newStr.lastIndexOf(indexStr)-1));//在结尾

        }else if(newStr.indexOf(indexStr) < (newStr.length() - indexStr.length())){
            newStr =  new StringBuilder(newStr.substring(0,newStr.indexOf(indexStr)-1)//在中间
                    +newStr.substring(newStr.indexOf(indexStr)+indexStr.length(),newStr.length()));

        }
        return newStr.toString();
    }
}
