package com.feuji.weekendtrip.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feuji.weekendtrip.model.Package;
import com.feuji.weekendtrip.model.Place;
import com.feuji.weekendtrip.repository.PackageRepository;
import com.feuji.weekendtrip.repository.PlaceRepository;
import com.feuji.weekendtrip.service.PlacesService;

@Service
public class PlacesServiceImplementation implements PlacesService {

	@Autowired(required = true)
	PackageRepository packageRepository;

	@Autowired(required = true)
	PlaceRepository placeRepository;

	@Override
	public Place savePlace(Place place) {
		List<Package> packageList = packageRepository.findAll();
		for (Package pack : packageList) {
			if (pack.getPackageId().equals(place.getPackages().getPackageId())) {
				place.setPackages(pack);
			}
		}
		return placeRepository.save(place);
	}

	@Override
	public List<Place> getPlaces() {
		return placeRepository.findAll();
	}

	@Override
	public Place getPlaceById(long placeId) {
		return placeRepository.findById(placeId).orElse(null);
	}

	@Override
	public Place updatePlace(long placeId, Place place) {
		place.setPlaceId(placeId);
		return placeRepository.save(place);
	}

	@Override
	public Place saveplaceById(long packId, Place place) {
		List<Package> packageList = packageRepository.findAll();
		for (Package pack : packageList) {
			if (pack.getPackageId().equals(packId)) {
				place.setPackages(pack);
			}
		}
		return placeRepository.save(place);
	}

	@Override
	public String changeStatus(long placeId) {
		List<Place> placeList = placeRepository.findAll();
		for (Place place : placeList) {
			if (place.getPlaceId().equals(placeId) && place.getPlaceStatus().equalsIgnoreCase("InActive")) {
				place.setPlaceStatus("Active");
			} else if (place.getPlaceId().equals(placeId) && place.getPlaceStatus().equalsIgnoreCase("Active")) {
				place.setPlaceStatus("InActive");
			}
			placeRepository.save(place);
		}
		return "Status changed" + placeId;
	}

}
