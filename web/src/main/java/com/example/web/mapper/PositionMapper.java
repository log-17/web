package com.example.web.mapper;

import com.example.web.entity.Position;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionMapper {

    Position selectPositionByOperatorId(Integer operatorId);

}
