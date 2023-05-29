package com.feuji.weekendtrip.service;

import java.util.List;

import com.feuji.weekendtrip.model.Passenger;

public interface PassengersService {

	Passenger savePassengers(Passenger passengers);

	List<Passenger> getPassengers();

	String deletePasssengerById(long passengerId);

	Passenger getPassengerById(long travellerId);


}
