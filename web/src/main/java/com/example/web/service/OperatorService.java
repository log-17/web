package com.example.web.service;

import com.example.web.entity.Operator;

public interface OperatorService {

    int insert(Operator operator);

    Operator queryByOperatorCode(String operatorCode);

}
