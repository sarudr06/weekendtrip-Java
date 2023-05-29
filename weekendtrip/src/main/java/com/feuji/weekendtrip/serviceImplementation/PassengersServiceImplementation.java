package com.feuji.weekendtrip.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feuji.weekendtrip.model.Passenger;
import com.feuji.weekendtrip.repository.PassengerRepository;
import com.feuji.weekendtrip.service.PassengersService;
@Service
public class PassengersServiceImplementation implements PassengersService {

	
	@Autowired(required = true)
	PassengerRepository passengerRepository;

	@Override
	public Passenger savePassengers(Passenger passengers) {
		return passengerRepository.save(passengers);
	}

	@Override
	public List<Passenger> getPassengers() {
		return passengerRepository.findAll();
	}

	@Override
	public String deletePasssengerById(long passengerId) {
		passengerRepository.deleteById(passengerId);
		return "passengers deleted " + passengerId;
	}

	@Override
	public Passenger getPassengerById(long passengerId) {
		return passengerRepository.findById(passengerId).orElse(null);
	}

}
