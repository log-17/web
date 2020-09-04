package com.example.web.session;

import com.alibaba.druid.support.json.JSONUtils;
import com.example.web.entity.Menu;
import com.example.web.entity.Operator;
import com.example.web.entity.Position;
import com.example.web.mapper.PositionMapper;
import com.example.web.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;

@Service
public class OperatorSessionHandler {

    @Autowired
    PositionMapper positionMapper;

    @Autowired
    MenuMapper menuMapper;

    private static final Logger logger = Logger.getLogger(OperatorSessionHandler.class.getName());

    public void createOperatorSession(HttpServletRequest request, HttpServletResponse response, Operator operator) {
        logger.info("＝＝＝创建操作员会话＝＝＝");
        Position position = positionMapper.selectPositionByOperatorId(operator.getId());
        List<Menu> menuList = menuMapper.selectRelatedMenu(position.getPositionId());

    }

}
