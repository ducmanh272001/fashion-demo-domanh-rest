package com.fashion.entity;

import java.util.Date;
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
@Table(name = "tbl_hoa_don")
public class HoaDon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "NGAY_BAN")
	private Date ngayban;
	@Column(name = "NAME_NN")
	private String nameKH;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "SDT")
	private String sdt;
	@Column(name = "STATUS")
	private boolean status;
	@ManyToOne
	@JoinColumn(name = "MA_KH", referencedColumnName = "IDKH")
	private KhachHang makh;
	@OneToMany(mappedBy = "id_hoadon", fetch = FetchType.EAGER)
	private List<HoaDonChiTiet> listHoaDonCt;
	@Transient
	private int idmakh;
	@Transient
	private List<Integer> sohdChitiet;

	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HoaDon(int id, Date ngayban, String nameKH, String address, String sdt, boolean status, KhachHang makh) {
		super();
		this.id = id;
		this.ngayban = ngayban;
		this.nameKH = nameKH;
		this.address = address;
		this.sdt = sdt;
		this.status = status;
		this.makh = makh;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getNgayban() {
		return ngayban;
	}

	public void setNgayban(Date ngayban) {
		this.ngayban = ngayban;
	}

	public String getNameKH() {
		return nameKH;
	}

	public void setNameKH(String nameKH) {
		this.nameKH = nameKH;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public KhachHang getMakh() {
		return makh;
	}

	public void setMakh(KhachHang makh) {
		this.makh = makh;
	}

	public List<HoaDonChiTiet> getListHoaDonCt() {
		return listHoaDonCt;
	}

	public void setListHoaDonCt(List<HoaDonChiTiet> listHoaDonCt) {
		this.listHoaDonCt = listHoaDonCt;
	}

	public int getIdmakh() {
		return idmakh;
	}

	public void setIdmakh(int idmakh) {
		this.idmakh = idmakh;
	}

	public List<Integer> getSohdChitiet() {
		return sohdChitiet;
	}

	public void setSohdChitiet(List<Integer> sohdChitiet) {
		this.sohdChitiet = sohdChitiet;
	}

	@Override
	public String toString() {
		return "HoaDon [id=" + id + ", ngayban=" + ngayban + ", nameKH=" + nameKH + ", address=" + address + ", sdt="
				+ sdt + ", status=" + status + ", makh=" + makh.getName() + ", listHoaDonCt=" + listHoaDonCt + "]";
	}

}
