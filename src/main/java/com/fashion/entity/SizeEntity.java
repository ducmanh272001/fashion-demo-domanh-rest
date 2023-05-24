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
@Table(name = "SIZE")
public class SizeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "STATUS")
	private boolean status;
	@OneToMany(mappedBy = "idkc",fetch = FetchType.EAGER)
	private List<ProductDetailEntity> listSanPhamDetail;

	public SizeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SizeEntity(int id, String name, boolean status, List<ProductDetailEntity> listSanPhamDetail) {
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

	public List<ProductDetailEntity> getListSanPhamDetail() {
		return listSanPhamDetail;
	}

	public void setListSanPhamDetail(List<ProductDetailEntity> listSanPhamDetail) {
		this.listSanPhamDetail = listSanPhamDetail;
	}

	@Override
	public String toString() {
		return "Kichco [id=" + id + ", name=" + name + ", status=" + status + ", listSanPhamDetail=" + listSanPhamDetail
				+ "]";
	}

}
