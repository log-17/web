package com.example.web.controller;

import com.example.web.entity.Operator;
import com.example.web.service.OperatorService;
import com.example.web.util.DateUtil;
import com.example.web.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
@ComponentScan({"com.example.web.service"})
public class RegisterController {

    private static final Logger logger = Logger.getLogger(RegisterController.class.getName());

    @Autowired
    OperatorService operatorService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        logger.info("＝＝＝进入注册页面＝＝＝");
        return "register";
    }

    @RequestMapping(value = "/verifyRegister", method = RequestMethod.POST)
    public String verifyRegister(Model model, @RequestParam String operatorCode, @RequestParam String operatorName, @RequestParam String operatorPassword) {
        if (operatorService.queryByOperatorCode(operatorCode) == null) {
            logger.info("＝＝＝注册成功＝＝＝");
            Operator operator = new Operator();
            operator.setOperatorCode(operatorCode);
            operator.setOperatorName(operatorName);
            operator.setOperatorPassword(EncryptUtil.md5Encrypt(operatorPassword));
            operator.setCreateDate(DateUtil.convertToStr(new Date(), DateUtil.DATE_TYPE_TIMESTAMP));
            operatorService.insert(operator);
            model.addAttribute("msg","注册成功！");
        } else {
            logger.info("＝＝＝注册失败，工号已经存在＝＝＝");
            model.addAttribute("msg", "工号已经存在！");
        }
        return "register";
    }
}
