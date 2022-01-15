package com.study.sortAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName Insert
 * @Description 插入算法
 * @Author
 * @Date 2022/1/4 10:18
 * @Version 1.0
 **/
public class InsertSort {
    public static void main(String[] args){
        int[] array = new int[80000];
        for (int i = 0 ; i<80000; i++){
            array[i] = (int)(Math.random()*80000);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        System.out.println("排序前："+simpleDateFormat.format(new Date()));
        sortOne(array);  //移动法
        //移动法效率高
        sortTwo(array); //交换法
        System.out.println("排序后："+simpleDateFormat.format(new Date()));

    }

    private static void sortOne(int[] array) {
        for (int i = 1; i< array.length;i++){
            int insertValue = array[i];
            int insertIndex = i -1;
            while (insertIndex>=0 && insertValue < array[insertIndex]){
                array[insertIndex+1] = array[insertIndex];
                insertIndex--;
            }

            array[insertIndex+1] = insertValue;

//            System.out.println("第"+(i)+"次排序："+ Arrays.toString(array));
        }
    }

    private static void sortTwo(int[] array) {
        for(int i = 1; i<array.length;i++){
            for (int j=0; j< i;j++){
                if(array[j]>array[i]){
                    int value = array[j];
                    array[j] = array[i];
                    array[i] = value;
                }
            }
//            System.out.println("第"+(i)+"次排序："+ Arrays.toString(array));
        }

    }
}
