package com.study.xmlAnalysis;

public class Area {
    private int    id;
    private String name;
    private String areaType;
    private int    parentId;
    private int    ordering;
//    private String zip;

    private String phoneArea;

    public Area(){
        System.out.println("Area constructor..");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        System.out.println( " set id of Area:" + id);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println( " set name of Area:" + name);
        this.name = name;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        System.out.println( " set areaType of Area:" + areaType);
        this.areaType = areaType;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        System.out.println( " set parentId of Area:" + parentId);
        this.parentId = parentId;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        System.out.println( " set ordering of Area:" + ordering);
        this.ordering = ordering;
    }

	/*public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}*/

    public String getPhoneArea() {
        return phoneArea;
    }

    public void setPhoneArea(String phoneArea) {
        System.out.println( " set phoneArea of Area:" + phoneArea);
        this.phoneArea = phoneArea;
    }


}