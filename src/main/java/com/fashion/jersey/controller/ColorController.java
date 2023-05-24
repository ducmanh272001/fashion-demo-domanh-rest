package com.fashion.jersey.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import com.fashion.entity.ColorEntity;
import com.fashion.service.color.ColorServiceImpl;
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
