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
@Table(name = "tbl_ct_hoa_don")
public class HoaDonChiTiet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@ManyToOne
	@JoinColumn(name = "ID_HOADON", referencedColumnName = "ID")
	private HoaDon id_hoadon;
	@ManyToOne
	@JoinColumn(name = "ID_SP_DETAIL", referencedColumnName = "ID")
	private SanPhamChiTiet id_sp;
	@Column(name = "QUANTITY")
	private int quantity;
	@Column(name = "PRICE")
	private float price;
	@Transient
	private int idHoaDon;
	@Transient
	private int idSanPhamCt;

	public HoaDonChiTiet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HoaDonChiTiet(HoaDon id_hoadon, SanPhamChiTiet id_sp, int quantity, float price) {
		super();
		this.id_hoadon = id_hoadon;
		this.id_sp = id_sp;
		this.quantity = quantity;
		this.price = price;
	}

	public HoaDon getId_hoadon() {
		return id_hoadon;
	}

	public void setId_hoadon(HoaDon id_hoadon) {
		this.id_hoadon = id_hoadon;
	}

	public SanPhamChiTiet getId_sp() {
		return id_sp;
	}

	public void setId_sp(SanPhamChiTiet id_sp) {
		this.id_sp = id_sp;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdHoaDon() {
		return idHoaDon;
	}

	public void setIdHoaDon(int idHoaDon) {
		this.idHoaDon = idHoaDon;
	}

	public int getIdSanPhamCt() {
		return idSanPhamCt;
	}

	public void setIdSanPhamCt(int idSanPhamCt) {
		this.idSanPhamCt = idSanPhamCt;
	}
	
	

	@Override
	public String toString() {
		return "HoaDonChiTiet [id=" + id + ", id_hoadon=" + id_hoadon.getId() + ", id_sp=" + id_sp.getId()
				+ ", quantity=" + quantity + ", price=" + price + "]";
	}

}
