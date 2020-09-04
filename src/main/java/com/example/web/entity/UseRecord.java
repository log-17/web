package com.example.web.entity;

import com.example.web.util.DateUtil;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UseRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String serviceCode;

    private String createDate;

    private String isSuc;

    private String inputDate;

    private String outputDate;

    private Long timestampDifference;

    // 查询条件
    private Date startQDate;

    private Date endQDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getIsSuc() {
        return isSuc;
    }

    public void setIsSuc(String isSuc) {
        this.isSuc = isSuc;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public String getOutputDate() {
        return outputDate;
    }

    public void setOutputDate(String outputDate) {
        this.outputDate = outputDate;
    }

    public Long getTimestampDifference() {
        Long inputLong = Long.parseLong(this.getInputDate());
        Date inputDate = new Date(inputLong.longValue());
        Long outputLong = Long.parseLong(this.getOutputDate());
        Date outputDate = new Date(outputLong.longValue());
        timestampDifference = outputDate.getTime() - inputDate.getTime();
        return timestampDifference;
    }

    public void setTimestampDifference(Long timestampDifference) {
        this.timestampDifference = timestampDifference;
    }

    public Date getStartQDate() {
        return startQDate;
    }

    public void setStartQDate(String startQDate) {
        try {
            this.startQDate = new SimpleDateFormat(DateUtil.DATE_TYPE_YYYY_MM_DD).parse(startQDate);
        } catch (ParseException e) {
            this.startQDate = null;
        }
    }

    public Date getEndQDate() {
        return endQDate;
    }

    public void setEndQDate(String endQDate) {
        try {
            this.endQDate = new SimpleDateFormat(DateUtil.DATE_TYPE_YYYY_MM_DD).parse(endQDate);
        } catch (ParseException e) {
            this.endQDate = null;
        }
    }

}
