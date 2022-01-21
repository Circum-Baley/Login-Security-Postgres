//package com.userlogin.userApp.entities;
//
//import java.time.LocalDate;
//import java.util.Objects;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//@Entity
//@Table(name="supplier")
//public class Supplier {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
//	
//	@Column(name = "enterprice")
//	private String enterprice;
//	
//	@Column(name = "rut")
//	private String rut;
//	
//	
//	@Column(name = "hiringDate")//  ??????????????????????????????????????????
////	@Temporal(TemporalType.DATE)
//	private LocalDate hiringDate;
//
//
//	public Integer getId() {
//		return id;
//	}
//
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//
//	public String getEnterprice() {
//		return enterprice;
//	}
//
//
//	public void setEnterprice(String enterprice) {
//		this.enterprice = enterprice;
//	}
//
//
//	public String getRut() {
//		return rut;
//	}
//
//
//	public void setRut(String rut) {
//		this.rut = rut;
//	}
//
//
//	public LocalDate getHiringDate() {
//		return hiringDate;
//	}
//
//
//	public void setHiringDate(LocalDate hiringDate) {
//		this.hiringDate = hiringDate;
//	}
//
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(id);
//	}
//
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Supplier other = (Supplier) obj;
//		return Objects.equals(id, other.id);
//	}
//
//	
//
//}
