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
@Table(name = "tbl_hinh_anh")
public class Hinhanh {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "NAME")
	private String name;
	@ManyToOne
	@JoinColumn(name = "IDSP", referencedColumnName = "IDSP")
	private Sanpham idhinhanh;
	@Transient
	private int idsp;

	public Hinhanh(int id, String name, Sanpham idhinhanh) {
		super();
		this.id = id;
		this.name = name;
		this.idhinhanh = idhinhanh;
	}

	public Hinhanh() {
		super();
		// TODO Auto-generated constructor stub
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

	public Sanpham getIdhinhanh() {
		return idhinhanh;
	}

	public void setIdhinhanh(Sanpham idhinhanh) {
		this.idhinhanh = idhinhanh;
	}

	public int getIdsp() {
		return idsp;
	}

	public void setIdsp(int idsp) {
		this.idsp = idsp;
	}

	@Override
	public String toString() {
		return "Hinhanh [id=" + id + ", name=" + name + ", idhinhanh=" + idhinhanh.getName() + "]";
	}

}
