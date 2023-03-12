package com.fashion.jersey.api;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.fashion.DAO.HinhanhImpl;
import com.fashion.DAO.HoaDonChiTietIml;
import com.fashion.DAO.SanPhamChiTietImpl;
import com.fashion.DAO.SanPhamImpl;
import com.fashion.entity.Hinhanh;
import com.fashion.entity.SanPhamChiTiet;
import com.fashion.entity.Sanpham;
import com.fashion.entity.ThongBao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path(value = "/San-pham")
public class SanPhamApi {

	@Context
	ServletContext servletcontext;
	

	@GET
	@Path(value = "/list-san-pham")
	@Produces(MediaType.APPLICATION_JSON)
	public String listSanPham() {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().selectAll();
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	// Chức năng xóa sản phẩm
	@POST
	@Path(value = "/delete/{idxoa}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteSanPham(@PathParam(value = "idxoa") int idla,@Context HttpServletRequest request) {
		// Trước tiền là phải tìm id của hình ảnh và sản phẩm chi tiết;
		// Tìm id san phẩm chi tiết;
		List<SanPhamChiTiet> listhdct = SanPhamChiTietImpl.getNewSanPhamChiTiet().selectByIdSanPham(idla);
		for (int i = 0; i < listhdct.size(); i++) {
			Boolean xoaCtHoaDon = HoaDonChiTietIml.getNewHoaDonChiTiet().deleteIdSanPhamCT(listhdct.get(i).getId());
			if (xoaCtHoaDon) {
				System.out.println("Xóa hóa đơn chi tiết thành công");
			} else {
				System.out.println("Xóa hóa đơn chi tiết không thành công");
			}
		}
		
		for (int i = 0; i < listhdct.size(); i++) {
			//Sẽ nhảy đến xóa cái Sản phẩm chi tiết
			Boolean xoaSpCt = SanPhamChiTietImpl.getNewSanPhamChiTiet().delete(listhdct.get(i).getId());
			if(xoaSpCt) {
				System.out.println("Xóa sản phẩm chi tiết thành công");
			}else {
				System.out.println("Xóa sản phẩm chi tiết không thành công");
			}
		}
		//Nếu muốn xóa luôn cả file trên đây thì
		List<Hinhanh> listha = HinhanhImpl.getNewHinhAnh().selectByIdSanPham(idla);
		for (Hinhanh hinhanh : listha) {
			String tenanh = hinhanh.getName();
			String ddgoc = request.getServletContext().getRealPath("/public/img");
			File file = new File(ddgoc + File.separator + tenanh);
			Boolean xoaTc = file.delete();
			if (xoaTc) {
				System.out.println("Đã xóa file img thành công");
			} else {
				System.out.println("Xóa ko thành công");
			}
		}
		Boolean xoaHinhAnh = HinhanhImpl.getNewHinhAnh().deleteByIdSanPham(idla);
		if (xoaHinhAnh) {
			System.out.println("Xóa hình ảnh thành công");
		} else {
			System.out.println("Xóa hình ảnh không thành công");
		}
		Boolean xoaTc = SanPhamImpl.getNewSanPham().delete(idla);
		ThongBao tb = new ThongBao();
		if (xoaTc) {
			tb.setMacode(1);
			tb.setText("Xóa thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Xóa không thành công");
		}
		Gson gs = new Gson();
		String data = gs.toJson(tb);
		return data;
	}

	@POST
	@Path(value = "/insert-san-pham")
	@Produces(MediaType.APPLICATION_JSON)
	public String insertSanPham(String data) {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Sanpham hs = gs.fromJson(data, Sanpham.class);
		ThongBao tb = new ThongBao();
		Boolean themTc = SanPhamImpl.getNewSanPham().insert(hs);
		if (themTc) {
			tb.setMacode(hs.getId());
			tb.setText("Thêm thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Thêm thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;
	}

	@GET
	@Path(value = "/list-san-pham/dam")
	@Produces(MediaType.APPLICATION_JSON)
	public String listSanPhamDam() {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().selectDam();
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	// lấy list set cả bộ

	@GET
	@Path(value = "/list-san-pham/ca-bo")
	@Produces(MediaType.APPLICATION_JSON)
	public String listSanPhamCaBo() {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().selectCaBo();
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	// Lấy list sản phẩm áo wear
	@GET
	@Path(value = "/list-san-pham/ao")
	@Produces(MediaType.APPLICATION_JSON)
	public String listShirt() {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().selectAo();
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}
	// lấy list set cả bộ

	@GET
	@Path(value = "/list-san-pham/ao-dai")
	@Produces(MediaType.APPLICATION_JSON)
	public String listSanPhamAoDai() {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().selectAoDai();
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	@GET
	@Path(value = "/list-san-pham/quan")
	@Produces(MediaType.APPLICATION_JSON)
	public String listSanPhamQuan() {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().selectQuan();
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	@GET
	@Path(value = "/search-name/{idtim}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchSanPham(@PathParam(value = "idtim") String idla) {
		List<Sanpham> lsearch = SanPhamImpl.getNewSanPham().selectByName(idla);
		for (Sanpham sanpham : lsearch) {
			sanpham.setIdnhanhieu(sanpham.getIdnh().getId());
			sanpham.setIdtheloai(sanpham.getIdlsp().getId());
			sanpham.setIdlsp(null);
			sanpham.setListSanPhamCt(null);
			sanpham.setIdnh(null);
			sanpham.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(lsearch);
		return data;
	}

	// Tìm kiếm theo id
	@GET
	@Path(value = "/search-id/{idla}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchIdSanPham(@PathParam(value = "idla") int idok) {
		Sanpham sp = SanPhamImpl.getNewSanPham().selectById(idok);
		sp.setIdnhanhieu(sp.getIdnh().getId());
		sp.setIdtheloai(sp.getIdlsp().getId());
		sp.setListSanPhamCt(null);
		sp.setIdlsp(null);
		sp.setIdnh(null);
		sp.setListHinhAnh(null);
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(sp);
		return data;
	}

	// Thêm update vào

	@POST
	@Path("/update-san-pham")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateSanPham(String data) {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Sanpham hs = gs.fromJson(data, Sanpham.class);
		ThongBao tb = new ThongBao();
		Boolean themTc = SanPhamImpl.getNewSanPham().update(hs);
		if (themTc) {
			tb.setMacode(1);
			tb.setText("Sửa thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Sửa thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;
	}

	// Lấy ra cái List Long
	@GET
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON)
	public String selectCount() {
		// Lấy ra cía list log
		Long list = SanPhamImpl.getNewSanPham().selectCount();
		Gson gs = new Gson();
		String data = gs.toJson(list);
		return data;
	}

	// Lấy ra số lượng sản phẩm của áo
	@GET
	@Path("/count-ao")
	@Produces(MediaType.APPLICATION_JSON)
	public String selectCountAo() {
		// Lấy ra cía list log
		Long list = SanPhamImpl.getNewSanPham().selectCountAo();
		Gson gs = new Gson();
		String data = gs.toJson(list);
		return data;
	}

	// Lấy ra số lượng sản phẩm của quần
	@GET
	@Path("/count-quan")
	@Produces(MediaType.APPLICATION_JSON)
	public String selectCountQuan() {
		// Lấy ra cía list log
		Long list = SanPhamImpl.getNewSanPham().selectCountQuan();
		Gson gs = new Gson();
		String data = gs.toJson(list);
		return data;
	}

	// Lấy ra số lượng sản phẩm của cả bộ
	@GET
	@Path("/count-ca-bo")
	@Produces(MediaType.APPLICATION_JSON)
	public String selectCountCaBo() {
		// Lấy ra cía list log
		Long list = SanPhamImpl.getNewSanPham().selectCoutCaBo();
		Gson gs = new Gson();
		String data = gs.toJson(list);
		return data;
	}

	// Lấy ra số lượng sản phẩm của aoas dài
	@GET
	@Path("/count-ao-dai")
	@Produces(MediaType.APPLICATION_JSON)
	public String selectCountAoDai() {
		// Lấy ra cía list log
		Long list = SanPhamImpl.getNewSanPham().selectCoutAoDai();
		Gson gs = new Gson();
		String data = gs.toJson(list);
		return data;
	}

	// Lấy ra số lượng sản phẩm đầm
	@GET
	@Path("/count-dam")
	@Produces(MediaType.APPLICATION_JSON)
	public String selectCountDam() {
		// Lấy ra cía list log
		Long list = SanPhamImpl.getNewSanPham().selectCountDam();
		Gson gs = new Gson();
		String data = gs.toJson(list);
		return data;
	}

	// Sản phẩm mới nhất
	@GET
	@Path(value = "/san-pham-sale")
	@Produces(MediaType.APPLICATION_JSON)
	public String sanPhamSale() {
		List<Sanpham> spmnt = SanPhamImpl.getNewSanPham().sanPhamSale();
		for (Sanpham sp : spmnt) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(spmnt);
		return data;
	}

	// Lấy ra sản phẩm mới nhất
	// Sản phẩm mới nhất
	@GET
	@Path(value = "/san-pham-moi-nhat")
	@Produces(MediaType.APPLICATION_JSON)
	public String sanPhamMoiNhat() {
		List<Sanpham> spmnt = SanPhamImpl.getNewSanPham().sanPhamMoiNhat();
		for (Sanpham sp : spmnt) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(spmnt);
		return data;
	}

	// Phân trang sản phẩm 1 trang 6 sản phẩm
	@GET
	@Path(value = "/Phan-Trang/{soluong}")
	@Produces(MediaType.APPLICATION_JSON)
	public String PhanTrang(@PathParam(value = "soluong") int page) {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().phanTrang(page);
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	// Phân trang sản phẩm 1 trang 6 sản phẩm
	@GET
	@Path(value = "/Phan-Trang/Ao/{soluong}")
	@Produces(MediaType.APPLICATION_JSON)
	public String PhanTrangAo(@PathParam(value = "soluong") int page) {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().phanTrangAo(page);
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	// Phân trang áo tăng dần
	@GET
	@Path(value = "/Phan-Trang/Ao-Tang-Dan/{soluong}")
	@Produces(MediaType.APPLICATION_JSON)
	public String PhanTrangAoTangDan(@PathParam(value = "soluong") int page) {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().phanTrangAoTangDan(page);
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	// Phân trang áo tăng dần
	@GET
	@Path(value = "/Phan-Trang/Ao-Giam-Dan/{soluong}")
	@Produces(MediaType.APPLICATION_JSON)
	public String PhanTrangAoGiamDan(@PathParam(value = "soluong") int page) {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().phanTrangAoGiamDan(page);
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

//	//Phân trang đầm giảm dần
	@GET
	@Path(value = "/Phan-Trang/Dam-Giam-Dan/{soluong}")
	@Produces(MediaType.APPLICATION_JSON)
	public String PhanTrangDamGiamDan(@PathParam(value = "soluong") int page) {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().phanTrangDamGiamDan(page);
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	// Phân trang đầm tăng dần
	@GET
	@Path(value = "/Phan-Trang/Dam-Tang-Dan/{soluong}")
	@Produces(MediaType.APPLICATION_JSON)
	public String PhanTrangDamTangDan(@PathParam(value = "soluong") int page) {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().phanTrangDamTangDan(page);
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	// Phân trang cả bộ tăng dần
	@GET
	@Path(value = "/Phan-Trang/Ca-Bo-Tang-Dan/{soluong}")
	@Produces(MediaType.APPLICATION_JSON)
	public String PhanTrangCaBoTangDan(@PathParam(value = "soluong") int page) {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().phanTrangCaBoTangDan(page);
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	// Phân trang cả bộ giảm dần
	@GET
	@Path(value = "/Phan-Trang/Ca-Bo-Giam-Dan/{soluong}")
	@Produces(MediaType.APPLICATION_JSON)
	public String PhanTrangCaBoGiamDan(@PathParam(value = "soluong") int page) {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().phanTrangCaBoGiamDan(page);
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	// Phân trang Aó dài giảm dần
	@GET
	@Path(value = "/Phan-Trang/Ao-Dai-Giam-Dan/{soluong}")
	@Produces(MediaType.APPLICATION_JSON)
	public String PhanTrangAoDaiGiamDan(@PathParam(value = "soluong") int page) {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().phanTrangAoDaiGiamDan(page);
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	// Phân trang cả bộ tăng dần
	@GET
	@Path(value = "/Phan-Trang/Ao-Dai-Tang-Dan/{soluong}")
	@Produces(MediaType.APPLICATION_JSON)
	public String PhanTrangAoDaiTangDan(@PathParam(value = "soluong") int page) {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().phanTrangAoDaiTangDan(page);
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	// Phân trang quần tăng dần
	@GET
	@Path(value = "/Phan-Trang/Quan-Tang-Dan/{soluong}")
	@Produces(MediaType.APPLICATION_JSON)
	public String PhanTrangQuanTangDan(@PathParam(value = "soluong") int page) {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().phanTrangQuanTangDan(page);
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	// Phân trang quần giảm dần
	@GET
	@Path(value = "/Phan-Trang/Quan-Giam-Dan/{soluong}")
	@Produces(MediaType.APPLICATION_JSON)
	public String PhanTrangQuanGiamDan(@PathParam(value = "soluong") int page) {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().phanTrangQuanGiamDan(page);
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	// Phân trang sản phẩm 1 trang 8 sản phẩm
	@GET
	@Path(value = "/Phan-Trang/Quan/{soluong}")
	@Produces(MediaType.APPLICATION_JSON)
	public String PhanTrangQuan(@PathParam(value = "soluong") int page) {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().phanTrangQuan(page);
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListHinhAnh(null);
			sp.setListSanPhamCt(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	// Phân trang sản phẩm 1 trang 6 sản phẩm
	@GET
	@Path(value = "/Phan-Trang/Ca-bo/{soluong}")
	@Produces(MediaType.APPLICATION_JSON)
	public String PhanTrangCaBo(@PathParam(value = "soluong") int page) {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().phanTrangCaBo(page);
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	// Phân trang sản phẩm 1 trang 6 sản phẩm
	@GET
	@Path(value = "/Phan-Trang/Ao-dai/{soluong}")
	@Produces(MediaType.APPLICATION_JSON)
	public String PhanTrangAoDai(@PathParam(value = "soluong") int page) {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().phanTrangAoDai(page);
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	// Phân trang sản phẩm 1 trang 6 sản phẩm
	@GET
	@Path(value = "/Phan-Trang/Dam/{soluong}")
	@Produces(MediaType.APPLICATION_JSON)
	public String PhanTrangDam(@PathParam(value = "soluong") int page) {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().phanTrangDam(page);
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	// Sản phảm tăng dần
	@GET
	@Path(value = "/list-san-pham-tang-dan")
	@Produces(MediaType.APPLICATION_JSON)
	public String listSanPhamTangDan() {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().selectAllAscending();
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	// Sản phẩm giảm dần
	@GET
	@Path(value = "/list-san-pham-giam-dan")
	@Produces(MediaType.APPLICATION_JSON)
	public String listSanPhamGiamDan() {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().selectAllDecrease();
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	// Sản phẩm mới nhất của áo
	@GET
	@Path(value = "/san-pham-moi-nhat-ao")
	@Produces(MediaType.APPLICATION_JSON)
	public String sanPhamAoMoiNhat() {
		List<Sanpham> spmnt = SanPhamImpl.getNewSanPham().sanPhamAoMoiNhat();
		for (Sanpham sp : spmnt) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(spmnt);
		return data;
	}

	/// Sản phẩm quần mới nhất
	@GET
	@Path(value = "/san-pham-moi-nhat-quan")
	@Produces(MediaType.APPLICATION_JSON)
	public String sanPhamQuanMoiNhat() {
		List<Sanpham> spmnt = SanPhamImpl.getNewSanPham().sanPhamQuanMoiNhat();
		for (Sanpham sp : spmnt) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListHinhAnh(null);
			sp.setListSanPhamCt(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(spmnt);
		return data;
	}

	@GET
	@Path(value = "/san-pham-moi-nhat-ao-dai")
	@Produces(MediaType.APPLICATION_JSON)
	public String sanPhamAoDaiMoiNhat() {
		List<Sanpham> spmnt = SanPhamImpl.getNewSanPham().sanPhamAoDaiMoiNhat();
		for (Sanpham sp : spmnt) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(spmnt);
		return data;
	}

	@GET
	@Path(value = "/san-pham-moi-nhat-ca-bo")
	@Produces(MediaType.APPLICATION_JSON)
	public String sanPhamCaBoMoiNhat() {
		List<Sanpham> spmnt = SanPhamImpl.getNewSanPham().sanPhamCaBoMoiNhat();
		for (Sanpham sp : spmnt) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(spmnt);
		return data;
	}

	@GET
	@Path(value = "/san-pham-moi-nhat-dam")
	@Produces(MediaType.APPLICATION_JSON)
	public String sanPhamDamMoiNhat() {
		List<Sanpham> spmnt = SanPhamImpl.getNewSanPham().sanPhamDamMoiNhat();
		for (Sanpham sp : spmnt) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(spmnt);
		return data;
	}

	// Phân trang sản phẩm giảm dần
	@GET
	@Path(value = "/Phan-Trang/San-Pham-Giam-Dan/{soluong}")
	@Produces(MediaType.APPLICATION_JSON)
	public String PhanTrangSanPhamGiamDan(@PathParam(value = "soluong") int page) {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().phanTrangSanPhamGiamDan(page);
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	// Phân trang sản phẩm tăng dần
	@GET
	@Path(value = "/Phan-Trang/San-Pham-Tang-Dan/{soluong}")
	@Produces(MediaType.APPLICATION_JSON)
	public String PhanTrangSanPhamTangDan(@PathParam(value = "soluong") int page) {
		List<Sanpham> listSp = SanPhamImpl.getNewSanPham().phanTrangSanPhamTangDan(page);
		for (Sanpham sp : listSp) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(listSp);
		return data;
	}

	// Sản phẩm theo giá 100 - 200
	@GET
	@Path(value = "/san-pham-100-200")
	@Produces(MediaType.APPLICATION_JSON)
	public String sanPham100den200() {
		List<Sanpham> spmnt = SanPhamImpl.getNewSanPham().gia100den200();
		for (Sanpham sp : spmnt) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(spmnt);
		return data;
	}

	// Sản phẩm theo giá 200 - 300
	@GET
	@Path(value = "/san-pham-200-300")
	@Produces(MediaType.APPLICATION_JSON)
	public String sanPham200den300() {
		List<Sanpham> spmnt = SanPhamImpl.getNewSanPham().gia200den300();
		for (Sanpham sp : spmnt) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(spmnt);
		return data;
	}

	// Sản phẩm theo giá 300 - 500
	@GET
	@Path(value = "/san-pham-300-500")
	@Produces(MediaType.APPLICATION_JSON)
	public String sanPham300den500() {
		List<Sanpham> spmnt = SanPhamImpl.getNewSanPham().gia300den500();
		for (Sanpham sp : spmnt) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(spmnt);
		return data;
	}

	@GET
	@Path(value = "/san-pham-gia-tren-500")
	@Produces(MediaType.APPLICATION_JSON)
	public String sanPhamTren500() {
		List<Sanpham> spmnt = SanPhamImpl.getNewSanPham().giaTren500();
		for (Sanpham sp : spmnt) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(spmnt);
		return data;
	}

	// Tìm kiếm theo loại sản phẩm
	@GET
	@Path(value = "/tim-nhan-hieu/{idla}/phan-trang/{idphan}")
	@Produces(MediaType.APPLICATION_JSON)
	public String timLoaiSanPham(@PathParam(value = "idla") int idla, @PathParam(value = "idphan") int idphan) {
		List<Sanpham> spmnt = SanPhamImpl.getNewSanPham().selectByBrand(idla, idphan);
		for (Sanpham sp : spmnt) {
			sp.setIdnhanhieu(sp.getIdnh().getId());
			sp.setIdtheloai(sp.getIdlsp().getId());
			sp.setTennh(sp.getIdnh().getName_brand());
			sp.setTenloai(sp.getIdlsp().getLoai_sp());
			sp.setIdlsp(null);
			sp.setIdnh(null);
			sp.setListSanPhamCt(null);
			sp.setListHinhAnh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(spmnt);
		return data;
	}

	// Đếm số nhãn hiệu
	@GET
	@Path(value = "/count-nhan-hieu/{idla}")
	@Produces(MediaType.APPLICATION_JSON)
	public String countNhanHieu(@PathParam(value = "idla") int idla) {
		Long countl = SanPhamImpl.getNewSanPham().selectSLBrand(idla);
		Gson gs = new Gson();
		String data = gs.toJson(countl);
		return data;
	}
}
