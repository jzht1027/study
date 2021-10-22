package com.study.Esper.contextTest;

import com.espertech.esper.client.*;
import com.study.Esper.Apple;

import java.util.HashSet;

/**
 * @ClassName HashContext
 * @Description
 * @Author
 * @Date 2021/3/30 10:37
 * @Version 1.0
 **/
public class HashContext {

    public static void main (String[] args){
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();
        EPAdministrator admin = epService.getEPAdministrator();
        EPRuntime runtime = epService.getEPRuntime();

        String esb = ESB.class.getName();
        String apple = Apple.class.getName();
        HashSet<String> hash_code = new HashSet<>();

        // 以java的hashcode方法计算sid的值(id必须大于5)，以CRC32算法计算id的值，然后对10取余后的值来建立context
        String epl = "create context HashPerson coalesce by hash_code(id) from "+ esb +"(id>1)," +
                " consistent_hash_crc32(id) from "+ apple +" granularity 3";

        EPStatement state = admin.createEPL(epl);
        state.addListener(new ContextPropertiesListener2());

        ESB ESB1 =new ESB();
        ESB1.setId(1);
        hash_code.add("1");
        Apple Apple = new Apple();
        Apple.setId(1);
        runtime.sendEvent(hash_code);

        ESB ESB2 =new ESB();
        ESB2.setId(3);
        hash_code.add("3");
        runtime.sendEvent(hash_code);

        ESB ESB3 =new ESB();
        ESB3.setId(5);
        hash_code.add("5");
        runtime.sendEvent(hash_code);

        ESB ESB4 =new ESB();
        ESB4.setId(7);
        hash_code.add("7");
        runtime.sendEvent(hash_code);

        ESB ESB5 =new ESB();
        ESB5.setId(9);
        hash_code.add("9");
        runtime.sendEvent(hash_code);

        ESB ESB6 =new ESB();
        ESB6.setId(11);
        hash_code.add("10");
        runtime.sendEvent(hash_code);
    }
}
