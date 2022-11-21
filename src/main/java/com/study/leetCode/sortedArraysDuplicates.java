package com.study.leetCode;

/**
 * 删除【有序数组】中的重复重复项，返回新数组的长度
 */
public class sortedArraysDuplicates {
    public static void main(String[] args){

        int size = removeDuplicates(new int[]{0, 1, 2, 2, 3, 3, 4});
        System.out.println(size);
    }

    public static int removeDuplicates(int[] nums){
        if (nums.length == 0){
            return 0;
        }

        int i = 0;
        for(int j = 1;j<nums.length;j++){
            if(nums[i]!=nums[j]){
                i++;
                nums[i]=nums[j];
            }
        }
        return i;
    }
}
