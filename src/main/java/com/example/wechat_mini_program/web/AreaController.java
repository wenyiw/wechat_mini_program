package com.example.wechat_mini_program.web;

import com.example.wechat_mini_program.entity.Area;
import com.example.wechat_mini_program.service.AreaService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/*
@RestController has 2 parts:
@Controller: receive requests on database from the front end and respond
@ResponseBody: will return our object or content as http
 */
@RestController
@RequestMapping("/demo") //root directory
public class AreaController {
    //@RequestMapping is a router
    //Tomcat started on port(s): 8080 (http) with context path '' (in console)
    //so the address for "this is a controller" is http://localhost:8080/Controller
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String hello() {
        return ("Hello!");
    }

    @Autowired
    private AreaService areaService;

    @RequestMapping(value="arealist", method = RequestMethod.GET)
    private Map<String, Object> listArea() {
        Map<String, Object> map = new HashMap<>();
        List<Area> list = areaService.queryArea();
        map.put("arealist", list);
        return map;
    }

    @RequestMapping(value = "/getareabyid", method = RequestMethod.GET)
    private Map<String, Object> getAreaById(Integer areaId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Area area = areaService.queryAreaById(areaId);
        modelMap.put("area", area);
        return modelMap;
    }

    @RequestMapping(value = "/addarea", method = RequestMethod.POST)
    private Map<String, Object> addArea(@RequestBody Area area) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", areaService.addArea(area));
        return modelMap;
    }

    @RequestMapping(value = "/modifyarea", method = RequestMethod.POST)
    private Map<String, Object> modifyArea(@RequestBody Area area) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", areaService.modifyArea(area));
        return modelMap;
    }

    @RequestMapping(value = "/removearea", method = RequestMethod.GET)
    private Map<String, Object> removeArea(Integer areaId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", areaService.deleteArea(areaId));
        return modelMap;
    }

}
