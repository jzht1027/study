package com.study.xmlAnalysis;

import java.util.ArrayList;

/**
 * @ClassName student
 * @Description
 * @Author
 * @Date 2021/4/2 14:56
 * @Version 1.0
 **/
public class student {
    private String name;
    private String age;
    private String grade;
    private ArrayList<subject> list = new ArrayList<>();
    private String taskClass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public ArrayList<subject> getList() {
        return list;
    }

    public void setList(ArrayList<subject> list) {
        this.list = list;
    }

    public void add ( subject subjects ){
        System.out.println("add Subjects " + subjects.getName() + " to the Student: "+ this.getName());
        list.add( subjects );
    }


    public String getTaskClass() {
        return this.taskClass;
    }

    public void setTaskClass(String taskClass) {
        this.taskClass = taskClass;
    }
}
