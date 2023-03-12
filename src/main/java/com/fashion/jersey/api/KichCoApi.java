package com.fashion.jersey.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fashion.DAO.KichCoImpl;
import com.fashion.entity.Kichco;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path(value = "/kich-thuoc")
public class KichCoApi {

	@GET
	@Path(value = "")
	@Produces(MediaType.APPLICATION_JSON)
	public String kichThuoc() {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		List<Kichco> lst = KichCoImpl.getNewKichCo().selectAll();
		for (Kichco kc : lst) {
			kc.setListSanPhamDetail(null);
		}
		String data = gs.toJson(lst);
		return data;
	}
	
	@GET
	@Path(value = "/search-kc/{namela}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchKichCo(@PathParam(value = "namela")String namela) {
		Kichco kc = KichCoImpl.getNewKichCo().findByName(namela);
		kc.setListSanPhamDetail(null);
		Gson gs = new Gson();
		String data = gs.toJson(kc);
		return data;
	}
}
