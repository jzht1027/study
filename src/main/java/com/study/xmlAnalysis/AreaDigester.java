package com.study.xmlAnalysis;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName AreaDigester
 * @Description
 * @Author
 * @Date 2021/4/2 16:53
 * @Version 1.0
 **/
public class AreaDigester {

    public static void main (String[] args){
        File file = new File(".\\src/main/resources/viewcache2.xml");
        System.out.println(file.exists());

        Digester digester = new Digester();
        digester.setValidating(false);
        digester.addObjectCreate("viewcache/areas",ViewCache.class);
        digester.addObjectCreate("viewcache/areas/area",Area.class);
        digester.addSetProperties("viewcache/areas/area");

        digester.addSetNext("viewcache/areas/area","addArea");

        try {
            ViewCache viewCache = digester.parse(file);
            for (Area area :viewCache.getList()){
                System.out.println(area.getAreaType());
                System.out.println(area.getId());
                System.out.println(area.getName());
                System.out.println(area.getOrdering());
                System.out.println(area.getParentId());
                System.out.println(area.getPhoneArea());;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }
}
