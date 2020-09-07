package com.example.web.entity;

import java.io.Serializable;
import java.util.List;

public class OperatorSession implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sessionId;

    private String token;

    private Operator operator;

    private List<Menu> menuList;

    private String thisLoginTime;

    private String lastLoginTime;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public String getThisLoginTime() {
        return thisLoginTime;
    }

    public void setThisLoginTime(String thisLoginTime) {
        this.thisLoginTime = thisLoginTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

}
