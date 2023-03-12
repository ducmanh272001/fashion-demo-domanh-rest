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
@Table(name = "tbl_kich_co")
public class Kichco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "STATUS")
	private boolean status;
	@OneToMany(mappedBy = "idkc",fetch = FetchType.EAGER)
	private List<SanPhamChiTiet> listSanPhamDetail;

	public Kichco() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Kichco(int id, String name, boolean status, List<SanPhamChiTiet> listSanPhamDetail) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.listSanPhamDetail = listSanPhamDetail;
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

	public List<SanPhamChiTiet> getListSanPhamDetail() {
		return listSanPhamDetail;
	}

	public void setListSanPhamDetail(List<SanPhamChiTiet> listSanPhamDetail) {
		this.listSanPhamDetail = listSanPhamDetail;
	}

	@Override
	public String toString() {
		return "Kichco [id=" + id + ", name=" + name + ", status=" + status + ", listSanPhamDetail=" + listSanPhamDetail
				+ "]";
	}

}
