package com.study.xmlAnalysis;

import java.util.ArrayList;

/**
 * @ClassName ViewCache
 * @Description
 * @Author
 * @Date 2021/4/2 16:54
 * @Version 1.0
 **/
public class ViewCache {
    private ArrayList<Area> list = new ArrayList<>();

    public ArrayList<Area> getList() {
        return list;
    }

    public void setList(ArrayList<Area> list) {
        this.list = list;
    }

    public void addArea(Area area){
        System.out.println("addArea..");
        list.add(area);
    }
}
