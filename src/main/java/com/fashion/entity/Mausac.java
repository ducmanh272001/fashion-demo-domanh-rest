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
@Table(name = "tbl_mau_sac")
public class Mausac {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "STATUS")
	private boolean status;
	@OneToMany(mappedBy = "idms", fetch = FetchType.EAGER)
	private List<SanPhamChiTiet> lstChiTiet;

	public Mausac() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mausac(int id, String name, boolean status, List<SanPhamChiTiet> lstChiTiet) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.lstChiTiet = lstChiTiet;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<SanPhamChiTiet> getLstChiTiet() {
		return lstChiTiet;
	}

	public void setLstChiTiet(List<SanPhamChiTiet> lstChiTiet) {
		this.lstChiTiet = lstChiTiet;
	}

	@Override
	public String toString() {
		return "Mausac [id=" + id + ", name=" + name + ", status=" + status + ", lstChiTiet=" + lstChiTiet + "]";
	}

}
