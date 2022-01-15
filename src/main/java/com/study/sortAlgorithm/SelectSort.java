package com.study.sortAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName Select
 * @Description 选择算法
 * @Author
 * @Date 2022/1/4 10:18
 * @Version 1.0
 **/
public class SelectSort {
    public static void main(String[] args){
        int[] array = new int[80000];
        for (int i = 0 ; i<80000; i++){
            array[i] = (int)(Math.random()*80000);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        System.out.println("排序前："+simpleDateFormat.format(new Date()));

        sort(array);

        System.out.println("排序后："+simpleDateFormat.format(new Date()));
    }

    private static void sort(int[] array) {

        for (int i=0; i < array.length-1;i++){
            int minIndex = i;
            int min = array[i];
            for (int j = 1+i; j<array.length;j++){
                if (min > array[j]){
                    min = array[j];
                    minIndex = j;
                }
            }
            if (min!=array[i]){
                array[minIndex]=array[i];
                array[i] = min;
            }
//            System.out.println("第"+ (i+1) +"排序后:"+ Arrays.toString(array));
        }
    }
}
