package com.example.web.controller;

import com.example.web.entity.BootTablePage;
import com.example.web.entity.BootTableResult;
import com.example.web.entity.UseRecord;
import com.example.web.service.UseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
@ComponentScan({"com.example.web.service"})
public class UseRecordController {

    private static final Logger logger = Logger.getLogger(UseRecordController.class.getName());

    @Autowired
    UseRecordService useRecordService;

    @RequestMapping(value = "/queryUseRecord", method = RequestMethod.GET)
    public String queryUseRecord() {
        logger.info("＝＝＝进入使用记录查询页面＝＝＝");
        return "queryUseRecord";
    }

    @RequestMapping(value = "/useRecordSearch", method = RequestMethod.GET)
    @ResponseBody
    public BootTableResult<UseRecord> useRecordSearch(String serviceCode, String startDateStr, String endDateStr, BootTablePage bootTablePage) {
        logger.info("＝＝＝正在根据查询条件加载使用记录＝＝＝");
        BootTableResult<UseRecord> result = new BootTableResult<>();
        List<UseRecord> useRecordList = useRecordService.queryUseRecordByCondition(serviceCode, startDateStr, endDateStr, bootTablePage.getOffset(), bootTablePage.getLimit());
        int count = useRecordService.countUseRecordByCondition(serviceCode, startDateStr, endDateStr);
        result.setRows(useRecordList);
        result.setTotal(count);
        return result;
    }
}
