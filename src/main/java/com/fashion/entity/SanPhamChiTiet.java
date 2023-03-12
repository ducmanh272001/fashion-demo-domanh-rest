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
@Table(name = "tbl_san_pham_ct")
public class SanPhamChiTiet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "AMOUNT")
	private int amount;
	@ManyToOne
	@JoinColumn(name = "MA_MS", referencedColumnName = "ID")
	private Mausac idms;
	@ManyToOne
	@JoinColumn(name = "MA_KC", referencedColumnName = "ID")
	private Kichco idkc;
	@ManyToOne
	@JoinColumn(name = "MA_SP", referencedColumnName = "IDSP")
	private Sanpham mact;
	@OneToMany(mappedBy = "id_sp", fetch = FetchType.EAGER)
	private List<HoaDonChiTiet> listHoaDonCt;
	@Transient
	private String kichco_name;
	@Transient
	private String mausac_name;
	@Transient
	private String sanpham_name;

	public SanPhamChiTiet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SanPhamChiTiet(int id, int amount, Mausac idms, Kichco idkc, Sanpham mact) {
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

	public Mausac getIdms() {
		return idms;
	}

	public void setIdms(Mausac idms) {
		this.idms = idms;
	}

	public Kichco getIdkc() {
		return idkc;
	}

	public void setIdkc(Kichco idkc) {
		this.idkc = idkc;
	}

	public Sanpham getMact() {
		return mact;
	}

	public void setMact(Sanpham mact) {
		this.mact = mact;
	}

	public List<HoaDonChiTiet> getListHoaDonCt() {
		return listHoaDonCt;
	}

	public void setListHoaDonCt(List<HoaDonChiTiet> listHoaDonCt) {
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
