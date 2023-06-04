package com.fashion.jersey.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fashion.entity.ColorEntity;
import com.fashion.entity.NotificationEntity;
import com.fashion.entity.TypeProductEntity;
import com.fashion.service.color.ColorServiceImpl;
import com.fashion.service.typeproduct.TypeProductServiceImpl;
import com.google.gson.Gson;

@Path(value = "/api/v1/color")
public class ColorController {

	@GET
	@Path(value = "")
	@Produces(MediaType.APPLICATION_JSON)
	public String mauSac() {
		List<ColorEntity> lmausac = ColorServiceImpl.getNewColorEntity().selectAll();
		for (ColorEntity mausac : lmausac) {
			mausac.setLstChiTiet(null);
		}
		Gson gs = new Gson();
		String data = gs.toJson(lmausac);
		return data;
	}
	
	
	
	
	// Xóa
		@POST
		@Path(value = "/delete/{idxoa}")
		public String delete(@PathParam(value = "idxoa") int idxoa) {
			Gson gs = new Gson();
			NotificationEntity tb = new NotificationEntity();
			Boolean xoatc = ColorServiceImpl.getNewColorEntity().delete(idxoa);
			if (xoatc) {
				tb.setMacode(1);
				tb.setText("Xóa color thành công");
			} else {
				tb.setMacode(0);
				tb.setText("Xóa color không thành công");
			}
			String trave = gs.toJson(tb);
			return trave;
		}
	
	@GET
	@Path(value = "/count")
	public String count() {
		Long soluong = ColorServiceImpl.getNewColorEntity().count();
		Gson gs = new Gson();
		String data = gs.toJson(soluong);
		return data;
	}
	
	@POST
	@Path(value = "/insert")
	public String insertColor(String data) {
		Gson gs = new Gson();
		ColorEntity lsp = gs.fromJson(data, ColorEntity.class);
		Boolean themtc = ColorServiceImpl.getNewColorEntity().insert(lsp);
		NotificationEntity tb = new NotificationEntity();
		if (themtc) {
			tb.setMacode(1);
			tb.setText("Thêm màu sắc thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Thêm màu sắc thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;
	}
	

	@POST
	@Path(value = "/update")
	public String update(String data) {
		Gson gs = new Gson();
		ColorEntity lsp = gs.fromJson(data, ColorEntity.class);
		Boolean themtc = ColorServiceImpl.getNewColorEntity().update(lsp);
		NotificationEntity tb = new NotificationEntity();
		if (themtc) {
			tb.setMacode(1);
			tb.setText("Sửa màu sắc thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Sửa màu sắc thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;
	}
	
	//Tìm theo tên màu sắc
	@GET
	@Path(value = "/{tenla}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchMauSac(@PathParam(value = "tenla")String tenla) {
		//Tìm theo tên
		ColorEntity ms = ColorServiceImpl.getNewColorEntity().findByName(tenla);
		ms.setLstChiTiet(null);
		Gson gs = new Gson();
		String data = gs.toJson(ms);
		return data;
	}
	
	@GET
	@Path(value = "/id/{idla}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchIdMauSac(@PathParam(value = "idla")int idla) {
		//Tìm theo tên
		ColorEntity ms = ColorServiceImpl.getNewColorEntity().selectById(idla);
		ms.setLstChiTiet(null);
		Gson gs = new Gson();
		String data = gs.toJson(ms);
		return data;
	}
	
	// Phân trang sản phẩm
	@GET
	@Path(value = "/phan-trang/{idla}")
	public String phanTrang(@PathParam(value = "idla") int idla) {
		// Trả về 1 cái String mà giống mvc thôi
		Gson gs = new Gson();
		List<ColorEntity> list = ColorServiceImpl.getNewColorEntity().pageSize(idla);
		for (ColorEntity loaiSanPham : list) {
			loaiSanPham.setLstChiTiet(null);
		}
		String data = gs.toJson(list);
		return data;
	}
}
