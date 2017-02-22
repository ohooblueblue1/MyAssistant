package com.zdd.assistant.entity.cook;

/**
 * Project Name: MyAssistant
 * File Name:    CookMenu.java
 * ClassName:    CookMenu
 *
 * Description: 菜谱类别实体
 *
 * @author ZDD
 * @date 2017年02月22日 17:46
 */
public class CookMenu {
    private int id;
    private String name;
    private int imgId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}