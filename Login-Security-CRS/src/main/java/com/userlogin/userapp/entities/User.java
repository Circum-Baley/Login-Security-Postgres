package com.userlogin.userapp.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users") 
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;

	
	 

//	@ManyToMany(mappedBy = "user_role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	
//    @JoinTable(name = "userio_role",
//              joinColumns = @JoinColumn(name =  "user_id",ForeignKey = @ (name = "FK_USER")),
//              inverseJoinColumns = @JoinColumn(name = "role_id"))
//	
//	@JoinTable(name = "user_role",
//    joinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_USER")),
//    inverseJoinColumns = @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "FK_ROLE"))
//	)
//	
//	private List<Role> listaRoles = new ArrayList<Role>();
//
//
//	public List<Role> getListaRoles() {
//		return listaRoles;
//	}
//
//	public void setListaRoles(List<Role> listaRoles) {
//		this.listaRoles = listaRoles;
//	}

	
	//importante
//	private ArrayList<Role> role = new ArrayList<Role>();
//	public ArrayList<Role> getRole() {
//		return role;
//	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
