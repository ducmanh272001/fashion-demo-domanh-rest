package com.fashion.jersey.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import com.fashion.DAO.MauSacImpl;
import com.fashion.entity.Mausac;
import com.google.gson.Gson;

import javax.ws.rs.Produces;

@Path(value = "/Mau-sac")
public class MauSacApi {

	@GET
	@Path(value = "")
	@Produces(MediaType.APPLICATION_JSON)
	public String mauSac() {
		List<Mausac> lmausac = MauSacImpl.getNewMauSac().selectAll();
		for (Mausac mausac : lmausac) {
			mausac.setLstChiTiet(null);
		}
		Gson gs = new Gson();
		String data = gs.toJson(lmausac);
		return data;
	}
	
	
	//Tìm theo tên màu sắc
	@GET
	@Path(value = "/search-mausac/{tenla}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchMauSac(@PathParam(value = "tenla")String tenla) {
		//Tìm theo tên
		Mausac ms = MauSacImpl.getNewMauSac().findByName(tenla);
		ms.setLstChiTiet(null);
		Gson gs = new Gson();
		String data = gs.toJson(ms);
		return data;
	}
	
	@GET
	@Path(value = "/search-mausac-id/{idla}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchIdMauSac(@PathParam(value = "idla")int idla) {
		//Tìm theo tên
		Mausac ms = MauSacImpl.getNewMauSac().selectById(idla);
		ms.setLstChiTiet(null);
		Gson gs = new Gson();
		String data = gs.toJson(ms);
		return data;
	}
}
