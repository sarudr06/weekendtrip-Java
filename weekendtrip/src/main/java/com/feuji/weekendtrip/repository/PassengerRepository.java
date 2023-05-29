package com.feuji.weekendtrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feuji.weekendtrip.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long>{

}
