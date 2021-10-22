package com.study.Esper.Transaction;


import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

/**
 * @ClassName PersonListener
 * @Description
 * @Author
 * @Date 2021/3/25 15:19
 * @Version 1.0
 **/
public class PersonListener implements UpdateListener {
    @Override
    public void update(EventBean[] eventBeans, EventBean[] eventBeans1) {
        if (eventBeans != null) {
//            String age = eventBeans[0].get("age").toString();
//            System.out.println("Person's age is " + age);
            String phones = eventBeans[0].get("phones('home')").toString();
            System.out.println("Person's phones is " + phones);
            String address = eventBeans[0].get("address.road").toString();
            System.out.println("Person's address is " + address);
            String children = eventBeans[0].get("children[0]").toString();
            System.out.println("Person's children is " + children);
        }
    }
}
