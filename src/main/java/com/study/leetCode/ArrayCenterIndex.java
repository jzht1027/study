package com.study.leetCode;

import java.util.Arrays;

/**
 * 寻找数组的中心下标：数组中心下标的 左边总和 == 右边的总和； 取最左边的中心下标值；
 */
public class ArrayCenterIndex {
    public static void main(String[] args){
        int index = pivotIndex(new int[]{1, 7, 3, 6, 5, 6});
        System.out.println(index);
    }

    public static int pivotIndex(int[] nums){
        int sum = Arrays.stream(nums).sum();

        int leftSum = 0;

        for (int i =0;i<nums.length;i++){
            leftSum += nums[i];

            if(leftSum == sum){
                return i;
            }

            sum = sum-nums[i];
        }
        return -1;
    }
}
