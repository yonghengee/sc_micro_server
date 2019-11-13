package com.tx.txcall.common.utils;/**
 * Created by wyh in 2019/5/5 20:22
 **/

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @program:
 * @description: 订单编号生成-时间戳+random
 * @author: forever-wang
 * @create: 2019-05-05 20:22
 **/
public class RandomCodeUtils {
    public static String getOrderIdByTime() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        String result="";
        Random random=new Random();
        for(int i=0;i<3;i++){
            result+=random.nextInt(10);
        }
        return newDate+result;
    }

    public static void main(String[] args) {
        System.out.println(getOrderIdByTime());
    }
}
