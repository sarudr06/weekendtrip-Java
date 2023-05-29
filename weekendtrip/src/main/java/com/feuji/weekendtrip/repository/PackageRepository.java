package com.feuji.weekendtrip.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feuji.weekendtrip.model.Package;

public interface PackageRepository extends JpaRepository<Package, Long> {

	Optional<Package> findByPackageName(String packageName);

}
