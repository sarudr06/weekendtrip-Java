package com.feuji.weekendtrip.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "city")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cityId;

	private String cityName;

	private String status;

	private String cImageUrl;

	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Package> packages;

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getcImageUrl() {
		return cImageUrl;
	}

	public void setcImageUrl(String cImageUrl) {
		this.cImageUrl = cImageUrl;
	}

	public List<Package> getPackages() {
		return packages;
	}

	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public City(Long cityId, String cityName, String status, String cImageUrl, List<Package> packages) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.status = status;
		this.cImageUrl = cImageUrl;
		this.packages = packages;
	}

	public City() {
		super();
	}

}
