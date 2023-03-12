package com.fashion.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_user_role")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@ManyToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	private User userId;
	@ManyToOne
	@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
	private Roles roleId;
	@Transient
	private String name_Role_ID;

	public UserRole() {

	}
	
	
	
	

	public UserRole(User userId, Roles roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}





	public UserRole(int id, User userId, Roles roleId) {
		super();
		this.id = id;
		this.userId = userId;
		this.roleId = roleId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Roles getRoleId() {
		return roleId;
	}

	public void setRoleId(Roles roleId) {
		this.roleId = roleId;
	}
	
	public String getName_Role_ID() {
		return name_Role_ID;
	}



	public void setName_Role_ID(String name_Role_ID) {
		this.name_Role_ID = name_Role_ID;
	}



	@Override
	public String toString() {
		return "UserRole [id=" + id + ", userId=" + userId.getName() + ",userId=" + userId.getPassword() + ",userId="
				+ userId.getEnabled() + ", roleId=" + roleId.getName() + "]";
	}

}
