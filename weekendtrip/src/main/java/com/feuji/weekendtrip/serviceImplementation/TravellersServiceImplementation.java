package com.feuji.weekendtrip.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.feuji.weekendtrip.model.Traveller;
import com.feuji.weekendtrip.repository.TravellerRepository;
import com.feuji.weekendtrip.service.TravellersService;

@Service
public class TravellersServiceImplementation implements TravellersService {

	@Autowired(required = true)
	TravellerRepository travellerRepository;

	@Override
	public Traveller saveTraveller(Traveller travellers) {
		return travellerRepository.save(travellers);
	}

	@Override
	public List<Traveller> getTraveller() {
		return travellerRepository.findAll();
	}

	@Override
	public String deleteTravellerById(long travellerId) {
		travellerRepository.deleteById(travellerId);
		return "traveller deleted " + travellerId;
	}

	@Override
	public Traveller getTravellerById(long id) {
		return travellerRepository.findById(id).orElse(null);
	}

	@Override
	public Page<Traveller> findAll(Pageable paging) {
		return travellerRepository.findAll(paging);
	}

	@Override
	public List<Traveller> findByEmail(String email) {
		return travellerRepository.findByTravellerEmail(email).get();
	}

}
