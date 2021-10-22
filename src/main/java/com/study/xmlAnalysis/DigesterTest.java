package com.study.xmlAnalysis;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName DigesterTest
 * @Description
 * @Author
 * @Date 2021/4/2 13:57
 * @Version 1.0
 **/
public class DigesterTest {

    public static void main(String[] args){
        File file = new File(".\\src/main/resources/student.xml");

        System.out.println(file.exists());
        Digester digester = new Digester();
        digester.addObjectCreate("student","com.study.xmlAnalysis.student");
//        digester.addSetProperties("student");
        digester.addBeanPropertySetter("student","name");
        digester.addBeanPropertySetter("student","age");
        digester.addBeanPropertySetter("student","grade");

        digester.addObjectCreate("student/subject","com.study.xmlAnalysis.subject");
//        digester.addSetProperties("student/subject");
        digester.addBeanPropertySetter("student/subject","name");
        digester.addBeanPropertySetter("student/subject","teacher");

        digester.addSetNext("student/subject","add");

        try {
            student student = digester.parse(file);
            System.out.println(student.getName());
            System.out.println(student.getAge());
            System.out.println(student.getGrade());

            for(subject o :student.getList()){
                System.out.println("name:"+o.getName());
                System.out.println("teacher:"+o.getTeacher());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}