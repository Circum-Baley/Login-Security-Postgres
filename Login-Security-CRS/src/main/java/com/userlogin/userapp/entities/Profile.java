package com.userlogin.userapp.entities;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "profile")
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="profile_id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "birthDate")
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@OneToMany
	private List<Device> devices;
	
	
	
	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	@OneToOne
	@JoinColumn(name = "user_id_fk", referencedColumnName = "user_id")
	private User user;

	public Profile() {
	}

	public Profile(String name, String lastName, Date birthDate, User user) {
		this.name = name;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.user = user;
	}

	
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "\nPROFILE :\n\tID = " + id + "\n\tname=" + name + "\n\tlastName=" + lastName + "\n\tbirthDate=" + birthDate
				+ "\n\t\tuser :" + getUser();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profile other = (Profile) obj;
		return Objects.equals(id, other.id);
	}

}
