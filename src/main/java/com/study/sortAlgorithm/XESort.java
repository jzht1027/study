package com.study.sortAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName XESort
 * @Description 希尔排序算法
 * @Author
 * @Date 2022/1/5 10:15
 * @Version 1.0
 **/
public class XESort {
    public static void main(String[] args){
        int[] array = new int[80000];
        for (int i = 0 ; i<80000; i++){
            array[i] = (int)(Math.random()*80000);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        System.out.println("排序前："+simpleDateFormat.format(new Date()));
        sortOne(array);  //移动法
        //移动法效率高
//        sortTwo(array); //交换法
        System.out.println("排序后："+simpleDateFormat.format(new Date()));

    }

    private static void sortOne(int[] array) {
        int count = 0;
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int j = i;
                int temp = array[i];
                if (array[j] < array[j - gap]){
                    while (j-gap >=0 && temp < array[j - gap]){
                        array[j] = array[j-gap];
                        j-=gap;
                    }
                    array[j] = temp;
                }
            }
//            System.out.println("第" + (++count) + "次排序：" + Arrays.toString(array));
        }
    }

    private static void sortTwo(int[] array) {

        int temp = 0;
        int count = 0;
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (array[j] > array[j + gap]) {
                        temp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = temp;
                    }
                }
            }
//            System.out.println("第" + (++count) + "次排序：" + Arrays.toString(array));
        }
    }
}
