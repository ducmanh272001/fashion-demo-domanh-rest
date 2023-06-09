package com.fashion.jersey.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fashion.entity.NotificationEntity;
import com.fashion.entity.RoleEntity;
import com.fashion.entity.TypeProductEntity;
import com.fashion.service.role.RoleServiceImpl;
import com.fashion.service.typeproduct.TypeProductServiceImpl;
import com.google.gson.Gson;

@Path(value = "/api/v1/role")
public class RoleController {

	@GET
	@Path(value = "/list")
	@Produces(MediaType.APPLICATION_JSON)
	public String listRole() {
		List<RoleEntity> list = RoleServiceImpl.getNewRole().selectAll();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setListUserRole(null);
		}
		Gson gs = new Gson();
		String data = gs.toJson(list);
		return data;
	}
	
	@GET
	@Path(value = "/search/{idla}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchRole(@PathParam(value = "idla")int idla) {
		RoleEntity role = RoleServiceImpl.getNewRole().selectById(idla);
		role.setListUserRole(null);
		Gson gs = new Gson();
		String data = gs.toJson(role);
		return data;
	}
	
	
	@POST
	@Path(value = "/insert")
	public String save(String data) {
		Gson gs = new Gson();
		RoleEntity lsp = gs.fromJson(data, RoleEntity.class);
		Boolean themtc = RoleServiceImpl.getNewRole().insert(lsp);
		NotificationEntity tb = new NotificationEntity();
		if (themtc) {
			tb.setMacode(1);
			tb.setText("Thêm ROLE thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Thêm ROLE thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;
	}
	
	@POST
	@Path(value = "/update")
	public String update(String data) {
		Gson gs = new Gson();
		RoleEntity lsp = gs.fromJson(data, RoleEntity.class);
		Boolean themtc = RoleServiceImpl.getNewRole().update(lsp);
		NotificationEntity tb = new NotificationEntity();
		if (themtc) {
			tb.setMacode(1);
			tb.setText("Sửa ROLE thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Sửa ROLE thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;
	}
}
