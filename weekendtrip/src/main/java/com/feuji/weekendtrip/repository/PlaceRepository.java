package com.feuji.weekendtrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feuji.weekendtrip.model.Place;


public interface PlaceRepository extends JpaRepository<Place,Long>{

}
