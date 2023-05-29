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
import com.feuji.weekendtrip.service.PackagesService;

@Service
public class PackagesServiceImplementation implements PackagesService {
	@Autowired(required = true)
	CityRepository cityRepository;

	@Autowired(required = true)
	PackageRepository packageRepository;

	@Autowired(required = true)
	PlaceRepository placeRepository;

	@Override
	public Package savePackage(Package pack) {
		List<City> cities = cityRepository.findAll();
		for (City city : cities) {
			if (city.getCityId().equals(pack.getCity().getCityId())) {
				pack.setCity(city);
			}
		}
		return packageRepository.save(pack);
	}

	@Override
	public List<Package> getPack() {
		return packageRepository.findAll();
	}

	@Override
	public Package getPackById(long packid) {
		return packageRepository.findById(packid).orElse(null);
	}

	@Override
	public Package updatepack(long packid, Package pack) {
		pack.setPackageId(packid);
		return packageRepository.save(pack);
	}

	@Override
	public Package savepackageById(long cityid, Package pack) {
		List<City> cityList = cityRepository.findAll();
		for (City city : cityList) {
			if (city.getCityId().equals(cityid)) {
				pack.setCity(city);
			}
		}
		return packageRepository.save(pack);
	}

	@Override
	public String changeStatus(long packId) {
		List<Package> packageList = packageRepository.findAll();
		List<Place> placeList = placeRepository.findAll();
		for (Package packages : packageList) {
			if (packages.getPackageId().equals(packId) && packages.getPackageStatus().equalsIgnoreCase("InActive")) {
				packages.setPackageStatus("Active");
			} else if (packages.getPackageId().equals(packId)
					&& packages.getPackageStatus().equalsIgnoreCase("Active")) {
				packages.setPackageStatus("InActive");
			}
			packageRepository.save(packages);
		}
		for (Place place : placeList) {
			if (place.getPackages().getPackageId().equals(packId)
					&& place.getPackages().getPackageStatus().equalsIgnoreCase("Active")) {
				place.setPlaceStatus("Active");
			} else if (place.getPackages().getPackageStatus().equalsIgnoreCase("InActive")) {
				place.setPlaceStatus("InActive");
			}
			placeRepository.save(place);
		}
		return "Status Changed" + packId;
	}

	@Override
	public Package findbypackName(String packageName) {
		return packageRepository.findByPackageName(packageName).get();
	}

}
