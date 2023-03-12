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
@Table(name = "tbl_roles")
public class Roles {

	// Mối quan hệ many to many
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID")
	private int id;
	@Column(name = "NAME")
	private String name;
	@OneToMany(mappedBy = "roleId", fetch = FetchType.EAGER)
	private List<UserRole> listUserRole;

	public Roles() {

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

	public List<UserRole> getListUserRole() {
		return listUserRole;
	}

	public void setListUserRole(List<UserRole> listUserRole) {
		this.listUserRole = listUserRole;
	}

	@Override
	public String toString() {
		return "Roles [id=" + id + ", name=" + name + ", listUserRole=" + listUserRole + "]";
	}

}
