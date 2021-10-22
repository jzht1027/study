package com.study.Esper.Transaction;

/**
 * @ClassName Address
 * @Description
 * @Author
 * @Date 2021/3/25 15:16
 * @Version 1.0
 **/
public class Address {
    String road;
    String street;
    int houseNo;
    // 省略getter setter方法


    public String getRoad() {
        return road;
    }

    public String getStreet() {
        return street;
    }

    public int getHouseNo() {
        return houseNo;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNo(int houseNo) {
        this.houseNo = houseNo;
    }
}
