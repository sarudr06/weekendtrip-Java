package com.feuji.weekendtrip.service;

import java.util.List;

import com.feuji.weekendtrip.model.Package;

public interface PackagesService  {

	Package savePackage(Package package1);

	List<Package> getPack();

	Package getPackById(long packId);

	Package updatepack(long packId, Package packages);

	Package savepackageById(long cityId, Package package1);

	String changeStatus(long packId);
	
	Package findbypackName(String packageName);
}
