package com.itheima.service;

import com.itheima.domain.City;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CityService {

    List<City> findCity();
}
