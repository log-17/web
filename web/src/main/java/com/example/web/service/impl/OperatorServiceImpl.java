package com.example.web.service.impl;

import com.example.web.entity.Operator;
import com.example.web.mapper.OperatorMapper;
import com.example.web.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    OperatorMapper operatorMapper;

    @Override
    public int insert(Operator operator) {
        return operatorMapper.insert(operator);
    }

    @Override
    public Operator queryByOperatorCode(String operatorCode) {
        return operatorMapper.queryByOperatorCode(operatorCode);
    }

}
