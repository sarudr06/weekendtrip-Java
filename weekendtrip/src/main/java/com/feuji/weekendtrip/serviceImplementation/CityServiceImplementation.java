package com.feuji.weekendtrip.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feuji.weekendtrip.model.City;
import com.feuji.weekendtrip.model.Package;
import com.feuji.weekendtrip.model.Place;
import com.feuji.weekendtrip.repository.CityRepository;
import com.feuji.weekendtrip.repository.PackageRepository;
import com.feuji.weekendtrip.repository.PlaceRepository;
import com.feuji.weekendtrip.service.CityService;

@Service
public class CityServiceImplementation implements CityService {

	@Autowired(required = true)
	CityRepository cityRepository;

	@Autowired(required = true)
	PackageRepository packageRepository;

	@Autowired(required = true)
	PlaceRepository placeRepository;

	@Override
	public City saveCity(City city) {
		return cityRepository.save(city);
	}

	@Override
	public List<City> getCities() {
		return cityRepository.findAll();
	}

	@Override
	public City getById(long cityId) {
		return cityRepository.findById(cityId).orElse(null);
	}

	@Override
	public City updateCity(long cityId, City city) {
		city.setCityId(cityId);
		return cityRepository.save(city);
	}

	@Override
	public String changeStatus(long cityId) {
		List<City> cityList = cityRepository.findAll();
		List<Package> packageList = packageRepository.findAll();
		List<Place> placesList = placeRepository.findAll();

		for (City city : cityList) {
			if (city.getCityId().equals(cityId) && city.getStatus().equalsIgnoreCase("Inactive")) {
				city.setStatus("Active");
			} else if (city.getCityId().equals(cityId) && city.getStatus().equalsIgnoreCase("Active")) {
				city.setStatus("InActive");
			}
			cityRepository.save(city);
		}
		for (Package pack : packageList) {
			if (pack.getCity().getCityId().equals(cityId) && pack.getCity().getStatus().equalsIgnoreCase("InActive")) {
				pack.setPackageStatus("InActive");
			} else if (pack.getCity().getCityId().equals(cityId)
					&& pack.getCity().getStatus().equalsIgnoreCase("Active")) {
				pack.setPackageStatus("Active");
			}
			packageRepository.save(pack);
		}
		for (Place place : placesList) {
			if (place.getPackages().getCity().getCityId().equals(cityId)
					&& place.getPackages().getPackageStatus().equalsIgnoreCase("InActive")) {
				place.setPlaceStatus("InActive");
			} else if (place.getPackages().getCity().getCityId().equals(cityId)
					&& place.getPackages().getPackageStatus().equalsIgnoreCase("Active")) {
				place.setPlaceStatus("Active");
			}
			placeRepository.save(place);
		}
		return "Status is changed";
	}

}
