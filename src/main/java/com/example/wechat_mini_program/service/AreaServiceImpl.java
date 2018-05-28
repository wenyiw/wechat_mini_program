package com.example.wechat_mini_program.service;

import com.example.wechat_mini_program.dao.AreaDao;
import com.example.wechat_mini_program.entity.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> queryArea() {
        return areaDao.queryArea();
    }

    @Override
    public Area queryAreaById(int areaId) {
        return areaDao.queryAreaById(areaId);
    }

    @Transactional
    @Override
    public boolean addArea(Area area) {
        if (area.getAreaName() != null && !"".equals(area.getAreaName())) {
            area.setCreateTime(new Date());
            area.setLastEditTime(new Date());
            try {
                int result = areaDao.insertArea(area);
                if (result > 0) return true;
                else throw new RuntimeException("Failed to insert");
            } catch (Exception e) {
                throw new RuntimeException("Failed to insert" + e.toString());
            }
        }
        else throw new RuntimeException("Area name is empty/null");
    }

    @Transactional
    @Override
    public boolean modifyArea(Area area) {
        if (area.getAreaId() != null && area.getAreaId() > 0) {
            area.setLastEditTime(new Date());
            try {
                int result = areaDao.updateArea(area);
                if (result > 0) return true;
                else throw new RuntimeException("Failed to update");
            } catch (Exception e) {
                throw new RuntimeException("Failed to update" + e.toString());
            }
        }
        else throw new RuntimeException("Area id is not valid");
    }

    @Transactional
    @Override
    public boolean deleteArea(int areaId) {
        if (areaId > 0) {
            try {
                int result = areaDao.deleteArea(areaId);
                if (result > 0) return true;
                else throw new RuntimeException("Failed to delete");
            } catch (Exception e) {
                throw new RuntimeException("Failed to delete" + e.toString());
            }
        }
        else throw new RuntimeException("Area id is not valid");
    }
}
