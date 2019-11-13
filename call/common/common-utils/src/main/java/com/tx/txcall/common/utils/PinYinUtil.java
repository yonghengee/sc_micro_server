package com.tx.txcall.common.utils;/**
 * Created by wyh in 2019/5/13 16:45
 **/

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * @program:
 * @description: 拼音工具类
 * @author: forever-wang
 * @create: 2019-05-13 16:45
 **/
public class PinYinUtil {

    /**
     * 获取汉字首字母的方法。如： 张三 --> ZS
     * 说明：暂时解决不了多音字的问题，只能使用取多音字的第一个音的方案
     * @param hanzi 汉子字符串
     * @return 大写汉子首字母; 如果都转换失败,那么返回null
     */
    public static String getHanziInitials(String hanzi) {
        String result = null;
        if(null != hanzi && !"".equals(hanzi)) {
            char[] charArray = hanzi.toCharArray();
            StringBuffer sb = new StringBuffer();
            for (char ch : charArray) {
                // 逐个汉字进行转换， 每个汉字返回值为一个String数组（因为有多音字）
                String[] stringArray = PinyinHelper.toHanyuPinyinStringArray(ch);
                if(null != stringArray) {
                    sb.append(stringArray[0].charAt(0));
                }
            }
            if(sb.length() > 0) {
                result = sb.toString().toUpperCase();
            }
        }
        return result;
    }
    /**

     * 获取汉字拼音的方法。如： 张三 --> zhangsan

     * 说明：暂时解决不了多音字的问题，只能使用取多音字的第一个音的方案

     * @param hanzi 汉子字符串

     * @return 汉字拼音; 如果都转换失败,那么返回null

     */
    public static String getHanziPinYin(String hanzi) {
        String result = null;
        if(null != hanzi && !"".equals(hanzi)) {
            char[] charArray = hanzi.toCharArray();
            StringBuffer sb = new StringBuffer();
            for (char ch : charArray) {
                // 逐个汉字进行转换， 每个汉字返回值为一个String数组（因为有多音字）
                String[] stringArray = PinyinHelper.toHanyuPinyinStringArray(ch);
                if(null != stringArray) {
                    // 把第几声这个数字给去掉
                    sb.append(stringArray[0].replaceAll("\\d", ""));
                }
            }
            if(sb.length() > 0) {
                result = sb.toString();
            }
        }
        return result;
    }
}
