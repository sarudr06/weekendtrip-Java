package com.feuji.weekendtrip.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "packages")
public class Package implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long packageId;

	private String packageName;

	private String packageCategory;

	private Double packagePrice;

	private String packageStatus;

	private String pImageUrl;

	private String packageDescription;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "cityIdRef")
	@JsonBackReference
	private City city;

	@OneToMany(mappedBy = "packages", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Place> places;

	public Long getPackageId() {
		return packageId;
	}

	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageCategory() {
		return packageCategory;
	}

	public void setPackageCategory(String packageCategory) {
		this.packageCategory = packageCategory;
	}

	public Double getPackagePrice() {
		return packagePrice;
	}

	public void setPackagePrice(Double packagePrice) {
		this.packagePrice = packagePrice;
	}

	public String getPackageStatus() {
		return packageStatus;
	}

	public void setPackageStatus(String packageStatus) {
		this.packageStatus = packageStatus;
	}

	public String getpImageUrl() {
		return pImageUrl;
	}

	public void setpImageUrl(String pImageUrl) {
		this.pImageUrl = pImageUrl;
	}

	public String getPackageDescription() {
		return packageDescription;
	}

	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Place> getPlaces() {
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Package(Long packageId, String packageName, String packageCategory, Double packagePrice,
			String packageStatus, String pImageUrl, String packageDescription, City city, List<Place> places) {
		super();
		this.packageId = packageId;
		this.packageName = packageName;
		this.packageCategory = packageCategory;
		this.packagePrice = packagePrice;
		this.packageStatus = packageStatus;
		this.pImageUrl = pImageUrl;
		this.packageDescription = packageDescription;
		this.city = city;
		this.places = places;
	}

	public Package() {
		super();
		// TODO Auto-generated constructor stub
	}

}
