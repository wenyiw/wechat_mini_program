package com.example.wechat_mini_program.dao;

import com.example.wechat_mini_program.entity.Area;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest {

    @Autowired
    private AreaDao areaDao;

    @Test
    public void queryArea() {
        List<Area> areaList = areaDao.queryArea();
        assertEquals(6, areaList.size());
    }

    @Test
    public void queryById() {
        Area area = areaDao.queryAreaById(1);
        assertEquals("BBB", area.getAreaName());
    }

    @Test
    public void insertArea() {
        Area area = new Area();
        area.setAreaName("Chrysler");
        area.setPriority(1);
        assertEquals(1, areaDao.insertArea(area));
    }

    @Test
    public void updateArea() {
        List<Area> areaList = areaDao.queryArea();
        for (Area area : areaList) {
            if ("EECS".equals(area.getAreaName())) {
                assertEquals(2, area.getPriority().intValue());
                area.setPriority(3);
                assertEquals(1, areaDao.updateArea(area));
            }
        }
    }

    @Test
    public void deleteArea() {
        assertEquals(1, areaDao.deleteArea(4));
        List<Area> areaList = areaDao.queryArea();
        assertEquals(5, areaList.size());
    }
}