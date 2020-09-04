package com.example.web.service.impl;

import com.example.web.entity.UseRecord;
import com.example.web.mapper.UseRecordMapper;
import com.example.web.service.UseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UseRecordServiceImpl implements UseRecordService {

    @Autowired
    UseRecordMapper useRecordMapper;

    @Override
    public List<UseRecord> queryUseRecordByCondition(String serviceCode, String startDateStr, String endDateStr, Integer startNum, Integer pageSize) {
        return useRecordMapper.queryUseRecordByCondition(serviceCode, startDateStr, endDateStr, startNum, pageSize);
    }

    @Override
    public int countUseRecordByCondition(String serviceCode, String startDateStr, String endDateStr) {
        return useRecordMapper.countUseRecordByCondition(serviceCode, startDateStr, endDateStr);
    }

}
