package com.fashion.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private int id;
	@Column(name = "USER_NAME")
	private String name;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "ENABLED")
	private int enabled;
	@Column(name = "FULL_NAME")
	private String fullName;
	@OneToMany(mappedBy = "userId", fetch = FetchType.EAGER)
	private List<UserRole> listUserRole;

	public User() {

	}

	public User(int id, String name, String password, int enabled, Roles roleId, String fullName) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.enabled = enabled;
		this.fullName = fullName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<UserRole> getListUserRole() {
		return listUserRole;
	}

	public void setListUserRole(List<UserRole> listUserRole) {
		this.listUserRole = listUserRole;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", enabled=" + enabled + ", fullName="
				+ fullName + ", listUserRole=" + listUserRole + "]";
	}

}
