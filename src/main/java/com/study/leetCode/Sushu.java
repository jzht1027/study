package com.study.leetCode;

/**
 * 获取素数个数-暴力算法、埃氏筛选
 */
public class Sushu {

    public static void main(String[] args){
        int bl = bl(100);
        System.out.println(bl);
        int eratosthenes = eratosthenes(100);
        System.out.println(eratosthenes);

    }

    //暴力算法
    public static int bl(int number){
        int count = 0;

        for(int i = 2; i<number;i++){
            count += isPrime(i)?1:0;
        }
        return count;
    }

    public static boolean isPrime(int x){
        for (int i =2; i<= Math.sqrt(x);i++){
            if (x%i==0){
                return false;
            }
        }
        return true;
    }

    //埃氏筛选  素数  非素数（合数） 12=2*6
    public static int eratosthenes(int number){
        boolean[] isPrime = new boolean[number]; //false 代表素数
        int count = 0;
        for (int i=2;i<number;i++){
            if(!isPrime[i]){
                count++;
                for(int j =i*i;j<number;j+=i){ //j就是合数的标记位
                    System.out.println("@@@@:"+j);
                    isPrime[j]=true;
                }
            }
        }
        return count;
    }
}
