package com.example.web.entity;

import java.io.Serializable;

public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer menuID;

    private String menuName;

    private String menuURL;

    private String menuType;

    private Integer menuLevel;

    private Integer menuStatus;

    private Integer parentMenuID;

    public Integer getMenuID() {
        return menuID;
    }

    public void setMenuID(Integer menuID) {
        this.menuID = menuID;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuURL() {
        return menuURL;
    }

    public void setMenuURL(String menuURL) {
        this.menuURL = menuURL;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public Integer getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(Integer menuStatus) {
        this.menuStatus = menuStatus;
    }

    public Integer getParentMenuID() {
        return parentMenuID;
    }

    public void setParentMenuID(Integer parentMenuID) {
        this.parentMenuID = parentMenuID;
    }

}
