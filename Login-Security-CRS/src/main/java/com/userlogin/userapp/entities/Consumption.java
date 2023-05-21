package com.userlogin.userapp.entities;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "consumption")
public class Consumption {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "consumption_id")
	private Integer id;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "number")
	private long number;

	@Column(name = "date_uploaded")
	private Date date;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(referencedColumnName = "vehicle_id", name = "vehicle_id_fk")
	private Vehicle vehicle;

	public Consumption() {
	}

	public Consumption(Double amount, long number, Date date, String description, Vehicle vehicle) {
		this.amount = amount;
		this.number = number;
		this.date = date;
		this.description = description;
		this.vehicle = vehicle;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		Consumption other = (Consumption) obj;
		return Objects.equals(id, other.id);
	}
}