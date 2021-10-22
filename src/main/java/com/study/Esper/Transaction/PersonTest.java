package com.study.Esper.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.espertech.esper.client.*;

/**
 * @ClassName PersonTest
 * @Description
 * @Author
 * @Date 2021/3/25 15:20
 * @Version 1.0
 **/
public class PersonTest {
    public static void main(String[] args) throws InterruptedException {

        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();

        EPAdministrator admin = epService.getEPAdministrator();

        String product = Person.class.getName();

        String name = "luonanqin";
        // 当Person类型的事件中name为luonanqin时，Esper能得到对应的age,children和address
//        String epl = "select age from "+ product +" where name=" + "\"" + name + "\"";
//
//        // 当Person类型的事件中name为luonanqin时，Esper能得到对应的第二个孩子,家里的电话和家庭住址在哪条路上
//        String epl = "select children[0], phones('home'), address.road " +
//                "from "+ product +" where name=" + "\"" + name + "\"";
//
        String epl = "update "+  product  +" set phones('home') = 123456789 where name=" + "\"" + name + "\"";

        EPStatement state = admin.createEPL(epl);
        state.addListener(new PersonListener());

        EPRuntime runtime = epService.getEPRuntime();

        Person Person = new Person();
        Person.setName("luonanqin");
        Person.setAge(23);

        Address address = new Address();
        address.setRoad("北京");
        Person.setAddress(address);

        List<Child> children = new ArrayList<>();
        Child Child = new Child();
        Child.setName("name");
        Child.setGender(123);
        children.add(Child);
        Person.setChildren(children);

        Map<String, Integer> phones = new HashMap<>();
//        phones.put("home",1517612565);
        Person.setPhones(phones);

        runtime.sendEvent(Person);

    }
}
