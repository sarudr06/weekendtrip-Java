package com.feuji.weekendtrip.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feuji.weekendtrip.model.Traveller;

public interface TravellerRepository extends JpaRepository<Traveller, Long> {
	
//    Page<Traveller> findByCity(String city);
	Optional<List<Traveller>> findByTravellerEmail(String travellerEmail);
	
	

}
