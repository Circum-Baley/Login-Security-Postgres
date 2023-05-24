package com.userlogin.userapp.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "device")
public class Device {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "device_id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "brand")
	private String brand;

	@Column(name = "model")
	private String model;

	@Column(name = "serialNumber")
	private String serialNumber;

	@Column(name = "dateAcquisition")
	private Date dateAcquisition;

	@ManyToOne
	@JoinColumn(name = "profile_id_fk", referencedColumnName = "profile_id")
	private Profile profile;

	public Device() {
	}

	public Device(Integer id, String name, String brand, String model, String serialNumber, Date dateAcquisition) {
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.model = model;
		this.serialNumber = serialNumber;
		this.dateAcquisition = dateAcquisition;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Date getDateAcquisition() {
		return dateAcquisition;
	}

	public void setDateAcquisition(Date dateAcquisition) {
		this.dateAcquisition = dateAcquisition;
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", name=" + name + ", brand=" + brand + ", model=" + model + ", serialNumber="
				+ serialNumber + ", dateAcquisition=" + dateAcquisition + ", profile=" + profile + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Device other = (Device) obj;
		return Objects.equals(id, other.id);
	}

}
