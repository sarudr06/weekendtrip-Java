package com.feuji.weekendtrip.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "places")
public class Place implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long placeId;

	private String placeName;

	private String placeStatus;

	private String placeImgUrl;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "packIdRef", referencedColumnName = "packageId")
	@JsonBackReference
	private Package packages;

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceStatus() {
		return placeStatus;
	}

	public void setPlaceStatus(String placeStatus) {
		this.placeStatus = placeStatus;
	}

	public String getPlaceImgUrl() {
		return placeImgUrl;
	}

	public void setPlaceImgUrl(String placeImgUrl) {
		this.placeImgUrl = placeImgUrl;
	}

	public Package getPackages() {
		return packages;
	}

	public void setPackages(Package packages) {
		this.packages = packages;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Place(Long placeId, String placeName, String placeStatus, String placeImgUrl, Package packages) {
		super();
		this.placeId = placeId;
		this.placeName = placeName;
		this.placeStatus = placeStatus;
		this.placeImgUrl = placeImgUrl;
		this.packages = packages;
	}

	public Place() {
		super();
		// TODO Auto-generated constructor stub
	}

}
