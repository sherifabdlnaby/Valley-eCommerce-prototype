package com.helloworld.entity;

public class Entity {

    private int id;
  //  private String name;
    private String desc;

    public Entity(int id, String name, String desc) {
        this.id = id;
      //  this.name = name;
        this.desc = desc;
    }

    public Entity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  //  public String getName() {
       // return name;
    //}

   // public void setName(String name) {
     //   this.name = name;
   // }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
