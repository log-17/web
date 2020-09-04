package com.example.web.entity;

import java.io.Serializable;

public class Operator implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer operatorId;

    private String operatorCode;

    private String operatorName;

    private String operatorPassword;

    private String createDate;

    public Integer getId() {
        return operatorId;
    }

    public void setId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorPassword() {
        return operatorPassword;
    }

    public void setOperatorPassword(String operatorPassword) {
        this.operatorPassword = operatorPassword;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString(){
        return getId() + " " + getOperatorCode() + " " + getOperatorName();
    }
}
