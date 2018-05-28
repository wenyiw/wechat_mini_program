package com.example.wechat_mini_program.service;

import com.example.wechat_mini_program.entity.Area;

import java.util.List;

public interface AreaService {
    List<Area> queryArea();
    Area queryAreaById(int areaId);
    boolean addArea(Area area);
    boolean modifyArea(Area area);
    boolean deleteArea(int areaId);
}
