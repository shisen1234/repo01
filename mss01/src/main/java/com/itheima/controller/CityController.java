package com.itheima.controller;

import com.itheima.domain.City;
import com.itheima.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @RequestMapping("/findCity")
    public @ResponseBody List<City> findCity() {
        System.out.println("findCity....执行");
        List<City> city = cityService.findCity();
        for (City city1 : city) {
            String name = city1.getName();
            System.out.println(name);
        }
        return city;
    }


}
