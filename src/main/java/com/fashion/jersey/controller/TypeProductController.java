package com.fashion.jersey.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.fashion.entity.TypeProductEntity;
import com.fashion.service.typeproduct.TypeProductServiceImpl;
import com.fashion.entity.NotificationEntity;
import com.google.gson.Gson;

@Path(value = "/api/v1/type-product")
public class TypeProductController {

	@GET
	@Path(value = "/list")
	public String ListTheLoai() {
		// Trả về 1 cái String mà giống mvc thôi
		Gson gs = new Gson();
		List<TypeProductEntity> list = TypeProductServiceImpl.getNewLoaiSp().selectAll();
		for (TypeProductEntity loaiSanPham : list) {
			loaiSanPham.setListSanPham(null);
		}
		String data = gs.toJson(list);
		return data;
	}

	// Phân trang sản phẩm
	@GET
	@Path(value = "/phan-trang/{idla}")
	public String phanTrang(@PathParam(value = "idla") int idla) {
		// Trả về 1 cái String mà giống mvc thôi
		Gson gs = new Gson();
		List<TypeProductEntity> list = TypeProductServiceImpl.getNewLoaiSp().phanTrangLoaiSanPham(idla);
		for (TypeProductEntity loaiSanPham : list) {
			loaiSanPham.setListSanPham(null);
		}
		String data = gs.toJson(list);
		return data;
	}

	@GET
	@Path(value = "/count")
	public String countDanhMuc() {
		Long soluong = TypeProductServiceImpl.getNewLoaiSp().selectCount();
		Gson gs = new Gson();
		String data = gs.toJson(soluong);
		return data;
	}

	// Thêm
	@POST
	@Path(value = "/insert")
	public String themDanhMuc(String data) {
		Gson gs = new Gson();
		TypeProductEntity lsp = gs.fromJson(data, TypeProductEntity.class);
		Boolean themtc = TypeProductServiceImpl.getNewLoaiSp().insert(lsp);
		NotificationEntity tb = new NotificationEntity();
		if (themtc) {
			tb.setMacode(1);
			tb.setText("Thêm danh mục thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Thêm danh mục thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;
	}

	// Sửa

	@POST
	@Path(value = "/update")
	public String suaDanhMuc(String data) {
		Gson gs = new Gson();
		TypeProductEntity lsp = gs.fromJson(data, TypeProductEntity.class);
		Boolean themtc = TypeProductServiceImpl.getNewLoaiSp().update(lsp);
		NotificationEntity tb = new NotificationEntity();
		if (themtc) {
			tb.setMacode(1);
			tb.setText("Sửa danh mục thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Sửa danh mục thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;
	}

	// Xóa
	@POST
	@Path(value = "/delete/{idxoa}")
	public String deleteDanhMuc(@PathParam(value = "idxoa") int idxoa) {
		Gson gs = new Gson();
		NotificationEntity tb = new NotificationEntity();
		Boolean xoatc = TypeProductServiceImpl.getNewLoaiSp().delete(idxoa);
		if (xoatc) {
			tb.setMacode(1);
			tb.setText("Xóa danh mục thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Xóa danh mục không thành công");
		}
		String trave = gs.toJson(tb);
		return trave;
	}

	// Search
	@GET
	@Path(value = "/search/{idla}")
	public String searchTheLoai(@PathParam(value = "idla")int idla) {
		// Trả về 1 cái String mà giống mvc thôi
		Gson gs = new Gson();
		TypeProductEntity lsp = TypeProductServiceImpl.getNewLoaiSp().selectById(idla);
		lsp.setListSanPham(null);
		String data = gs.toJson(lsp);
		return data;
	}
}
