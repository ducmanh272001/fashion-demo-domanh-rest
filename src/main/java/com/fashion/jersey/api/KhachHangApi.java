package com.fashion.jersey.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fashion.DAO.KhachHangDao;
import com.fashion.entity.KhachHang;
import com.fashion.entity.ThongBao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/KhachHang")
public class KhachHangApi {

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public String listKhachHang() {
		List<KhachHang> list = KhachHangDao.newKhachHang().selectAll();
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setListHoaDon(null);
		}
		String dulieu = gs.toJson(list);
		return dulieu;
	}

	// Thêm khách hàng
	@POST
	@Path(value = "/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public String themKhachHang(String dulieu) {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		KhachHang kh = gs.fromJson(dulieu, KhachHang.class);
		Boolean themtc = KhachHangDao.newKhachHang().insert(kh);
		ThongBao tb = new ThongBao();
		if (themtc) {
			tb.setMacode(kh.getId());
			tb.setText("Thêm dữ liệu thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Thêm dữ liệu thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;
	}

	// Sửa khách hàng
	@POST
	@Path(value = "/update")
	@Produces(MediaType.APPLICATION_JSON)
	public String SuaKhachHang(String dulieu) {
		Gson gs = new Gson();
		KhachHang kh = gs.fromJson(dulieu, KhachHang.class);
		Boolean themtc = KhachHangDao.newKhachHang().update(kh);
		ThongBao tb = new ThongBao();
		if (themtc) {
			tb.setMacode(1);
			tb.setText("Sửa dữ liệu thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Sửa dữ liệu thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;
	}

	// Tìm khách hàng
	@GET
	@Path(value = "/search-kh/{idla}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchIdSanPham(@PathParam(value = "idla") int idok) {
		KhachHang kh = KhachHangDao.newKhachHang().selectById(idok);
		kh.setListHoaDon(null);
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(kh);
		return data;
	}

	// Tìm số lượng khách hàng
	@GET
	@Path(value = "/count-khach-hang")
	@Produces(MediaType.APPLICATION_JSON)
	public String CountKhachHang() {
		Long soluong = KhachHangDao.newKhachHang().selectCountKh();
		Gson gs = new Gson();
		String dulieu = gs.toJson(soluong);
		return dulieu;
	}

	// Xóa khách hàng
	@POST
	@Path(value = "/deleteKhachHang/{idxoa}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteKhachHang(@PathParam(value = "idxoa") int idla) {
		Boolean xoatc = KhachHangDao.newKhachHang().delete(idla);
		ThongBao tb = new ThongBao();
		if (xoatc) {
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

	// pHÂN Trang khách hàng
	@GET
	@Path("/phan-trang-khach-hang/{sotrang}")
	@Produces(MediaType.APPLICATION_JSON)
	public String phanTrangKhachHang(@PathParam(value = "sotrang") int sotrang) {
		List<KhachHang> list = KhachHangDao.newKhachHang().phanTrangKhachHang(sotrang);
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setListHoaDon(null);
		}
		String dulieu = gs.toJson(list);
		return dulieu;
	}
}
