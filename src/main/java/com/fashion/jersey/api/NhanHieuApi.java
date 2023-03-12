package com.fashion.jersey.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fashion.DAO.NhanHieuImpl;
import com.fashion.entity.NhanHieu;
import com.google.gson.Gson;

@Path(value = "/Nhan-hieu")
public class NhanHieuApi {

	@GET
	@Path(value = "/list")
	@Produces(MediaType.APPLICATION_JSON)
	public String listNhanHieu() {
		//Lấy ra danh sách list nhan hiệu
		List<NhanHieu> list = NhanHieuImpl.getNewNhanHieu().selectAll();
		for (NhanHieu nhanHieu : list) {
			nhanHieu.setListSanPham(null);
		}
		Gson gs = new Gson();
		String data = gs.toJson(list);
		return data;
	}
}
