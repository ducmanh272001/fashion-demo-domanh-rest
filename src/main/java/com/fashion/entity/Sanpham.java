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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tbl_san_pham")
public class Sanpham {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDSP")
	private int id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "DESCRIPE")
	private String descripe;
	@Column(name = "INFORMATION")
	private String information;
	@Column(name = "PRICE_IMPORT")
	private float price_import;
	@Column(name = "PRICE_OLD")
	private float price_old;
	@Column(name = "PRICE_NEW")
	private float price_new;
	@Column(name = "QUANTITY")
	private int sp_view;
	@Column(name = "UPDATE_DAY")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date day_update;
	@Column(name = "STATUS")
	private boolean status;
	@ManyToOne
	@JoinColumn(name = "IDNH", referencedColumnName = "IDNH")
	private NhanHieu idnh;
	@ManyToOne
	@JoinColumn(name = "IDTYPE", referencedColumnName = "IDTYPE")
	private LoaiSanPham idlsp;
	@OneToMany(mappedBy = "mact", fetch = FetchType.EAGER)
	private List<SanPhamChiTiet> listSanPhamCt;

	@OneToMany(mappedBy = "idhinhanh", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Hinhanh> listHinhAnh;

	@Transient
	private String tennh;
	@Transient
	private String tenloai;
	@Transient
	private int idnhanhieu;
	@Transient
	private int idtheloai;

	public Sanpham() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sanpham(String name, String descripe, String information, float price_import, float price_old,
			float price_new, int sp_view, Date day_update, boolean status, NhanHieu idnh, LoaiSanPham idlsp,
			String img) {
		super();
		this.name = name;
		this.descripe = descripe;
		this.information = information;
		this.price_import = price_import;
		this.price_old = price_old;
		this.price_new = price_new;
		this.sp_view = sp_view;
		this.day_update = day_update;
		this.status = status;
		this.idnh = idnh;
		this.idlsp = idlsp;
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

	public String getDescripe() {
		return descripe;
	}

	public void setDescripe(String descripe) {
		this.descripe = descripe;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public float getPrice_import() {
		return price_import;
	}

	public void setPrice_import(float price_import) {
		this.price_import = price_import;
	}

	public float getPrice_old() {
		return price_old;
	}

	public void setPrice_old(float price_old) {
		this.price_old = price_old;
	}

	public float getPrice_new() {
		return price_new;
	}

	public void setPrice_new(float price_new) {
		this.price_new = price_new;
	}

	public int getSp_view() {
		return sp_view;
	}

	public void setSp_view(int sp_view) {
		this.sp_view = sp_view;
	}

	public Date getDay_update() {
		return day_update;
	}

	public void setDay_update(Date day_update) {
		this.day_update = day_update;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public NhanHieu getIdnh() {
		return idnh;
	}

	public void setIdnh(NhanHieu idnh) {
		this.idnh = idnh;
	}

	public LoaiSanPham getIdlsp() {
		return idlsp;
	}

	public void setIdlsp(LoaiSanPham idlsp) {
		this.idlsp = idlsp;
	}


	public List<SanPhamChiTiet> getListSanPhamCt() {
		return listSanPhamCt;
	}

	public void setListSanPhamCt(List<SanPhamChiTiet> listSanPhamCt) {
		this.listSanPhamCt = listSanPhamCt;
	}

	public String getTennh() {
		return tennh;
	}

	public void setTennh(String tennh) {
		this.tennh = tennh;
	}

	public String getTenloai() {
		return tenloai;
	}

	public void setTenloai(String tenloai) {
		this.tenloai = tenloai;
	}

	public int getIdnhanhieu() {
		return idnhanhieu;
	}

	public void setIdnhanhieu(int idnhanhieu) {
		this.idnhanhieu = idnhanhieu;
	}

	public int getIdtheloai() {
		return idtheloai;
	}

	public void setIdtheloai(int idtheloai) {
		this.idtheloai = idtheloai;
	}

	public List<Hinhanh> getListHinhAnh() {
		return listHinhAnh;
	}

	public void setListHinhAnh(List<Hinhanh> listHinhAnh) {
		this.listHinhAnh = listHinhAnh;
	}

	@Override
	public String toString() {
		return "Sanpham [id=" + id + ", name=" + name + ", descripe=" + descripe + ", information=" + information
				+ ", price_import=" + price_import + ", price_old=" + price_old + ", price_new=" + price_new
				+ ", sp_view=" + sp_view + ", day_update=" + day_update + ", status=" + status + ", idnh=" + idnh
				+ ", idlsp=" + idlsp + ", listSanPhamCt=" + listSanPhamCt + ", ListHinhAnh="
				+ listHinhAnh + "]";
	}

}
