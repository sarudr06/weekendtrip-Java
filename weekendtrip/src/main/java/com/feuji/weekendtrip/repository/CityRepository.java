package com.feuji.weekendtrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feuji.weekendtrip.model.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
