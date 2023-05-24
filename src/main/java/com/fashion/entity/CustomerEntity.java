package com.fashion.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "`CODE`")
	private String code;
	@Column(name = "`NAME`")
	private String name;
	@Column(name = "`PASSWORD`")
	private String passwword;
	@Column(name = "`ADDRESS`")
	private String address;
	@Column(name = "`CALL`")
	private String call;
	@Column(name = "`EMAIL`")
	private String email;
	@Column(name = "`BIRTHDAY`")
	private Date birthday;
	@Column(name = "`GENDER`")
	private boolean gender;
	@Column(name = "`STATUS`")
	private boolean status;
	@OneToMany(mappedBy = "makh", fetch = FetchType.EAGER)
	private List<BillEntity> listHoaDon;

	public CustomerEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerEntity(int id, String name, String passwword, String address, String call, String email,
			Date birthday, boolean gender, boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.passwword = passwword;
		this.address = address;
		this.call = call;
		this.email = email;
		this.birthday = birthday;
		this.gender = gender;
		this.status = status;
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


	public String getPasswword() {
		return passwword;
	}

	public void setPasswword(String passwword) {
		this.passwword = passwword;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		this.call = call;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<BillEntity> getListHoaDon() {
		return listHoaDon;
	}

	public void setListHoaDon(List<BillEntity> listHoaDon) {
		this.listHoaDon = listHoaDon;
	}

	@Override
	public String toString() {
		return "KhachHang [id=" + id + ", name=" + name + ", passwword=" + passwword
				+ ", address=" + address + ", call=" + call + ", email=" + email + ", birthday=" + birthday
				+ ", gender=" + gender + ", status=" + status + ", listHoaDon=" + listHoaDon + "]";
	}

}
