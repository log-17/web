package com.example.web.session;

import com.alibaba.druid.filter.config.ConfigTools;
import com.example.web.entity.Menu;
import com.example.web.entity.Operator;
import com.example.web.entity.OperatorSession;
import com.example.web.entity.Position;
import com.example.web.mapper.MenuMapper;
import com.example.web.mapper.PositionMapper;
import com.example.web.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@Service
public class OperatorSessionHandler {

    @Autowired
    PositionMapper positionMapper;

    @Autowired
    MenuMapper menuMapper;

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final Logger logger = Logger.getLogger(OperatorSessionHandler.class.getName());

    public OperatorSession createOperatorSession(HttpServletRequest request, HttpServletResponse response, Operator operator) throws Exception {
        logger.info("＝＝＝创建操作员会话＝＝＝");
        // 根据操作员职位查找可用的所有菜单
        Position position = positionMapper.selectPositionByOperatorId(operator.getId());
        List<Menu> menuList = menuMapper.selectRelatedMenu(position.getPositionId());

        Map<Menu, List<Menu>> menuMap = new HashMap<>();
        for (int i = 0; i < menuList.size(); i++) {
            // 如果是父菜单
            if (menuList.get(i).getParentMenuId() == -1) {
                Integer parentId = menuList.get(i).getMenuId();
                List<Menu> childMenuList = new ArrayList<>();
                // 将同一父菜单的子菜单存入 childMenuList 并放入具有相同 key 的 menuMap 中
                for (int j = 0; j < menuList.size(); j++) {
                    if (menuList.get(j).getParentMenuId() == parentId) {
                        childMenuList.add(menuList.get(j));
                    }
                }
                menuMap.put(menuMapper.findMenuById(parentId), childMenuList);
            }
        }

        DateFormat df = new SimpleDateFormat(DATE_FORMAT);

        OperatorSession operatorSession = new OperatorSession();
        operatorSession.setSessionId(request.getSession().getId());
        operatorSession.setMenuMap(menuMap);
        operatorSession.setToken(ConfigTools.encrypt(operator.getId().toString()));
        operatorSession.setThisLoginTime(df.format(new Date()));
        operatorSession.setLastLoginTime(df.format(new Date()));

        Cookie cookie = new Cookie(ConstantUtil.OPERATOR_SESSION_KEY, operatorSession.getToken());
        cookie.setPath("/");
        response.addCookie(cookie);

        return operatorSession;
    }

    public void destroyOperatorSession(HttpServletRequest request, HttpServletResponse response) {
        logger.info("＝＝＝销毁操作员会话＝＝＝");
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(ConstantUtil.OPERATOR_SESSION_KEY)) {
                cookies[i].setMaxAge(0);
                response.addCookie(cookies[i]);
            }
        }
    }

}
