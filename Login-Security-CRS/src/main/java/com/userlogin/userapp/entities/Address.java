package com.userlogin.userapp.entities;

import java.util.Objects;
//	
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Integer id;

	@Column(name = "street_address")
	private String street;

	@Column(name = "building_number")
	private String number;

	@Column(name = "city_name")
	private String city_name;

	public Address() {
	}

	public Address(String street, String number, String city_name, Profile profile) {
		this.street = street;
		this.number = number;
		this.city_name = city_name;
		this.profile = profile;
	}

	@ManyToOne
	@JoinColumn(name = "profile_id_fk", referencedColumnName = "profile_id") // (cascade = CascadeType.REMOVE)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Profile profile;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

//
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "\nADDRESS \n\tID =" + id + ", \n\tCalle=" + street + ", \n\tNumero = " + number + ", \n\tCiudad = "
				+ city_name + ", \n\tprofile =" + profile;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(id, other.id);
	}
}
