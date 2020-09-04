package com.example.web.mapper;

import com.example.web.entity.Operator;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface OperatorMapper {

    int insert(Operator operator);

    Operator queryByOperatorCode(@Param("operatorCode") String operatorCode);

}
