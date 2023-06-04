package com.fashion.jersey.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fashion.entity.NotificationEntity;
import com.fashion.entity.UserRoleEntity;
import com.fashion.service.branch.BranchServiceImpl;
import com.fashion.service.userrole.UserRoleServiceImpl;
import com.google.gson.Gson;

@Path(value = "/api/v1/user-role")
public class UserRoleController {

	
	@GET
	@Path(value = "/list")
	@Produces(MediaType.APPLICATION_JSON)
	public String listUserRole() {
		Gson gs = new Gson();
		List<UserRoleEntity> list = UserRoleServiceImpl.getNewUserRole().selectAll();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setName_Role_ID(list.get(i).getRoleId().getName());
			list.get(i).setUserId(null);
			list.get(i).setRoleId(null);
		}
		String data = gs.toJson(list);
		return data;
	}
	
	
	@GET
	@Path(value = "/search/{idla}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchUserRole(@PathParam(value = "idla")int idla) {
		Gson gs = new Gson();
		UserRoleEntity user = UserRoleServiceImpl.getNewUserRole().selectById(idla);
		user.setName_Role_ID(user.getRoleId().getName());
		user.setRoleId(null);
		user.setUserId(null);
		String data = gs.toJson(user);
		return data;
	}
	
	
	
	@GET
	@Path(value = "/search-user/{idla}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchNguoiDung(@PathParam(value = "idla")int idla) {
		Gson gs = new Gson();
		UserRoleEntity user = UserRoleServiceImpl.getNewUserRole().selectByNguoiDung(idla);
		user.setName_Role_ID(user.getRoleId().getName());
		user.setRoleId(null);
		user.setUserId(null);
		String data = gs.toJson(user);
		return data;
	}
	
	
	// Xóa
	@POST
	@Path(value = "/delete/{idxoa}")
	public String delete(@PathParam(value = "idxoa") int idxoa) {
		Gson gs = new Gson();
		NotificationEntity tb = new NotificationEntity();
		Boolean xoatc = UserRoleServiceImpl.getNewUserRole().delete(idxoa);
		if (xoatc) {
			tb.setMacode(1);
			tb.setText("Xóa thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Xóa không thành công");
		}
		String trave = gs.toJson(tb);
		return trave;
	}
	
	
	@POST
	@Path(value = "/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public String insertUserRole(String data) {
		Gson gs = new Gson();
		UserRoleEntity user = gs.fromJson(data, UserRoleEntity.class);
		NotificationEntity tb = new NotificationEntity();
		Boolean themtc = UserRoleServiceImpl.getNewUserRole().insert(user);
		if(themtc) {
			tb.setMacode(1);
			tb.setText("Thêm thành công");
		}else {
			tb.setMacode(0);
			tb.setText("Thêm thất bại");
		}
		String dulieu = gs.toJson(tb);
		return dulieu;
	}
}
