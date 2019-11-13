package com.tx.txcall.common.utils;/**
 * Created by wyh in 2019/9/2 10:42
 **/

import java.util.ArrayList;
import java.util.List;

/**
 * @program:
 * @description: 集合分割工具
 * @author: forever-wang
 * @create: 2019-09-02 10:42
 **/
public class ListAverageUtils {

    /**
     * 将List分成n份
     *
     * @param source
     * @param n
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        //        System.out.println(source.size());
        List<List<T>> result = new ArrayList<List<T>>();
        int remainder = source.size() % n;  //(先计算出余数)
        int number = source.size() / n;  //然后是商
        int offset = 0;//偏移量
        for (int i = 0; i < n; i++) {
            List<T> value = null;
            if (remainder > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remainder--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }
}
