package com.feuji.weekendtrip.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "traveller")
@NamedQuery(name = "Traveller.findByPurchaseDate", query = "select u from Traveller u where u.purchaseDate = ?1")
@NamedQuery(name = "Traveller.findByCityName", query = "select u from Traveller u where u.cityName = ?1")
@NamedQuery(name = "Traveller.findByPackageName", query = "select u from Traveller u where u.packageName = ?1")
public class Traveller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long travellerId;

	private String travellerEmail;


	private Date journeyStartingDate;

	private Date journeyEndingDate;

	private String packageName;

	private double packagePrice;

	private Date purchaseDate;

	private boolean paymentStatus;

	private String cityName;

	@OneToMany(mappedBy = "traveller", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	List<Passenger> passenger;

	public Long getTravellerId() {
		return travellerId;
	}

	public void setTravellerId(Long travellerId) {
		this.travellerId = travellerId;
	}

	public String getTravellerEmail() {
		return travellerEmail;
	}

	public void setTravellerEmail(String travellerEmail) {
		this.travellerEmail = travellerEmail;
	}

	public Date getJourneyStartingDate() {
		return journeyStartingDate;
	}

	public void setJourneyStartingDate(Date journeyStartingDate) {
		this.journeyStartingDate = journeyStartingDate;
	}

	public Date getJourneyEndingDate() {
		return journeyEndingDate;
	}

	public void setJourneyEndingDate(Date journeyEndingDate) {
		this.journeyEndingDate = journeyEndingDate;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public double getPackagePrice() {
		return packagePrice;
	}

	public void setPackagePrice(double packagePrice) {
		this.packagePrice = packagePrice;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<Passenger> getPassenger() {
		return passenger;
	}

	public void setPassenger(List<Passenger> passenger) {
		this.passenger = passenger;
	}

	public Traveller(Long travellerId, String travellerEmail, Date journeyStartingDate, Date journeyEndingDate,
			String packageName, double packagePrice, Date purchaseDate, boolean paymentStatus, String cityName,
			List<Passenger> passenger) {
		super();
		this.travellerId = travellerId;
		this.travellerEmail = travellerEmail;
		this.journeyStartingDate = journeyStartingDate;
		this.journeyEndingDate = journeyEndingDate;
		this.packageName = packageName;
		this.packagePrice = packagePrice;
		this.purchaseDate = purchaseDate;
		this.paymentStatus = paymentStatus;
		this.cityName = cityName;
		this.passenger = passenger;
	}

	public Traveller() {
		super();
		// TODO Auto-generated constructor stub
	}

}
