package com.fashion.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PRODUCT_DETAIL")
public class ProductDetailEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "AMOUNT")
	private int amount;
	@ManyToOne
	@JoinColumn(name = "MA_MS", referencedColumnName = "ID")
	private ColorEntity idms;
	@ManyToOne
	@JoinColumn(name = "MA_KC", referencedColumnName = "ID")
	private SizeEntity idkc;
	@ManyToOne
	@JoinColumn(name = "MA_SP", referencedColumnName = "ID")
	private ProductEntity mact;
	@OneToMany(mappedBy = "id_sp", fetch = FetchType.EAGER)
	private List<BillDetailEntity> listHoaDonCt;
	@Transient
	private String kichco_name;
	@Transient
	private String mausac_name;
	@Transient
	private String sanpham_name;

	public ProductDetailEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductDetailEntity(int id, int amount, ColorEntity idms, SizeEntity idkc, ProductEntity mact) {
		super();
		this.id = id;
		this.amount = amount;
		this.idms = idms;
		this.idkc = idkc;
		this.mact = mact;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public ColorEntity getIdms() {
		return idms;
	}

	public void setIdms(ColorEntity idms) {
		this.idms = idms;
	}

	public SizeEntity getIdkc() {
		return idkc;
	}

	public void setIdkc(SizeEntity idkc) {
		this.idkc = idkc;
	}

	public ProductEntity getMact() {
		return mact;
	}

	public void setMact(ProductEntity mact) {
		this.mact = mact;
	}

	public List<BillDetailEntity> getListHoaDonCt() {
		return listHoaDonCt;
	}

	public void setListHoaDonCt(List<BillDetailEntity> listHoaDonCt) {
		this.listHoaDonCt = listHoaDonCt;
	}

	public String getKichco_name() {
		return kichco_name;
	}

	public void setKichco_name(String kichco_name) {
		this.kichco_name = kichco_name;
	}

	public String getMausac_name() {
		return mausac_name;
	}

	public void setMausac_name(String mausac_name) {
		this.mausac_name = mausac_name;
	}

	public String getSanpham_name() {
		return sanpham_name;
	}

	public void setSanpham_name(String sanpham_name) {
		this.sanpham_name = sanpham_name;
	}

	@Override
	public String toString() {
		return "SanPhamChiTiet [id=" + id + ", amount=" + amount + ", idms=" + idms.getName() + ", idkc="
				+ idkc.getName() + ", mact=" + mact.getName() + ", listHoaDonCt=" + listHoaDonCt + "]";
	}

}
