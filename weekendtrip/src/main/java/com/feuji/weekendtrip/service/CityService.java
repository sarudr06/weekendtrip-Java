package com.feuji.weekendtrip.service;

import java.util.List;

import com.feuji.weekendtrip.model.City;

public interface CityService {

	City saveCity(City city);

	List<City> getCities();

	City getById(long cityId);

	City updateCity(long cityId, City city);

	String changeStatus(long cityId);
}
