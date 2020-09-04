package com.example.web.mapper;

import com.example.web.entity.UseRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UseRecordMapper {

    List<UseRecord> queryUseRecordByCondition(@Param("serviceCode") String serviceCode, @Param("startDateStr") String startDateStr, @Param("endDateStr") String endDateStr, @Param("startNum") Integer startNum, @Param("pageSize") Integer pageSize);

    int countUseRecordByCondition(@Param("serviceCode") String serviceCode, @Param("startDateStr") String startDateStr, @Param("endDateStr") String endDateStr);

}
