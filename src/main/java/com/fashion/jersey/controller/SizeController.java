package com.fashion.jersey.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fashion.entity.SizeEntity;
import com.fashion.service.size.SizeServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path(value = "/api/v1/size")
public class SizeController {

	@GET
	@Path(value = "")
	@Produces(MediaType.APPLICATION_JSON)
	public String kichThuoc() {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		List<SizeEntity> lst = SizeServiceImpl.getNewKichCo().selectAll();
		for (SizeEntity kc : lst) {
			kc.setListSanPhamDetail(null);
		}
		String data = gs.toJson(lst);
		return data;
	}
	
	@GET
	@Path(value = "/{namela}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchKichCo(@PathParam(value = "namela")String namela) {
		SizeEntity kc = SizeServiceImpl.getNewKichCo().findByName(namela);
		kc.setListSanPhamDetail(null);
		Gson gs = new Gson();
		String data = gs.toJson(kc);
		return data;
	}
}
