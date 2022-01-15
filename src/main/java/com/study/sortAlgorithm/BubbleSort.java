package com.study.sortAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName Bubbling
 * @Description  冒泡算法
 * @Author
 * @Date 2022/1/4 10:17
 * @Version 1.0
 **/
public class BubbleSort {
    public static void main(String[] args){
        int[] array = new int[6];
        for (int i = 0 ; i<6; i++){
            array[i] = (int)(Math.random()*80000);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        System.out.println("排序前："+simpleDateFormat.format(new Date()));

        sort(array);

        System.out.println("排序后："+simpleDateFormat.format(new Date()));
    }

    public static void sort(int[] array){

        boolean flag = false;
        for (int j =0; j<array.length-1;j++){
            for (int i =0;i<array.length-1-j;i++){
                if(array[i]>array[i+1]) {
                    flag = true;
                    int value = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = value;
                }
            }
            System.out.println("第"+ (j+1) +"排序后:"+ Arrays.toString(array));
            if (!flag){
                return;
            }else {
                flag = false;
            }
        }
    }
}
