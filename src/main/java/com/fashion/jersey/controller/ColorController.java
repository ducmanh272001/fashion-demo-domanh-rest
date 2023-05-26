package com.fashion.jersey.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import com.fashion.entity.ColorEntity;
import com.fashion.entity.NotificationEntity;
import com.fashion.entity.TypeProductEntity;
import com.fashion.service.color.ColorServiceImpl;
import com.fashion.service.typeproduct.TypeProductServiceImpl;
import com.google.gson.Gson;

import javax.ws.rs.Produces;

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
}
