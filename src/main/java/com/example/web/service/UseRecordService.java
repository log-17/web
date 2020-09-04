package com.example.web.service;

import com.example.web.entity.UseRecord;

import java.util.List;

public interface UseRecordService {

    List<UseRecord> queryUseRecordByCondition(String serviceCode, String startDateStr, String endDateStr, Integer startNum, Integer pageSize);

    int countUseRecordByCondition(String serviceCode, String startDateStr, String endDateStr);

}
