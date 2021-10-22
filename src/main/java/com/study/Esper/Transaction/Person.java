package com.study.Esper.Transaction;


import java.util.List;
import java.util.Map;

/**
 * @ClassName Person
 * @Description
 * @Author
 * @Date 2021/3/25 15:07
 * @Version 1.0
 **/
public class Person {
    String name;
    int age;
    List<Child> children;
    Map<String, Integer> phones;
    Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public Map<String, Integer> getPhones() {
        return phones;
    }

    public void setPhones(Map<String, Integer> phones) {
        this.phones = phones;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
