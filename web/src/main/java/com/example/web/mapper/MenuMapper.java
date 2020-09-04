package com.example.web.mapper;

import com.example.web.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {

    List<Menu> selectRelatedMenu(Integer positionId);

}
