package com.example.web.controller;

import com.example.web.entity.Menu;
import com.example.web.entity.Operator;
import com.example.web.entity.OperatorSession;
import com.example.web.service.OperatorService;
import com.example.web.session.OperatorSessionHandler;
import com.example.web.util.ConstantUtil;
import com.example.web.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class.getName());

    @Autowired
    OperatorService operatorService;

    @Autowired
    OperatorSessionHandler operatorSessionHandler;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        logger.info("＝＝＝进入登录页面＝＝＝");
        return "login";
    }

    @RequestMapping(value = "/verifyLogin", method = RequestMethod.POST)
    public String verifyLogin(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam String operatorCode, @RequestParam String operatorPassword) throws Exception {
        logger.info("＝＝＝登录操作＝＝＝");
        System.out.println(request.getParameter("operatorCode"));
        Operator operator = operatorService.queryByOperatorCode(operatorCode);
        if (operator == null) {
            logger.info("登陆失败，工号不存在");
            model.addAttribute("msg", "账号或密码错误！");
        } else {
            if (EncryptUtil.md5Encrypt(operatorPassword).equals(operator.getOperatorPassword())) {
                OperatorSession operatorSession = operatorSessionHandler.createOperatorSession(request, response, operator);
                request.getSession().setAttribute(ConstantUtil.OPERATOR_SESSION_KEY, operatorSession);
                return "redirect:/index";
            } else {
                logger.info("登陆失败，用户密码错误");
                model.addAttribute("msg", "账号或密码错误！");
            }
        }
        return "login";
    }

    @RequestMapping(value = "/logOut", method = RequestMethod.GET)
    public String logOut(HttpServletRequest request, HttpServletResponse response) {
        logger.info("＝＝＝退出登录＝＝＝");
        operatorSessionHandler.destroyOperatorSession(request, response);
        return "redirect:/login";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        logger.info("＝＝＝进入后台主页面＝＝＝");
        OperatorSession operatorSession = (OperatorSession) request.getSession().getAttribute(ConstantUtil.OPERATOR_SESSION_KEY);
        Map<Menu, List<Menu>> menuMap = operatorSession.getMenuMap();
        model.addAttribute("menuMap", menuMap);
        return "index";
    }

}
