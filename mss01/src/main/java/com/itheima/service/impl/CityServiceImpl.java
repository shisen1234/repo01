package com.itheima.service.impl;

import com.itheima.domain.City;
import com.itheima.mapper.CityMapper;
import com.itheima.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityMapper cityMapper;
    @Override
    public List<City> findCity() {
        return cityMapper.findCity();
    }
}
