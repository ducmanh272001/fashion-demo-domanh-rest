package com.fashion.jersey.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fashion.entity.NotificationEntity;
import com.fashion.entity.UserEntity;
import com.fashion.service.user.UserServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path(value = "/api/v1/user")
public class UserController {

	@GET
	@Path(value = "")
	@Produces(MediaType.APPLICATION_JSON)
	public String listUser() {
		List<UserEntity> list = UserServiceImpl.getNewUser().selectAll();
		for (UserEntity user : list) {
			user.setListUserRole(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(list);
		return data;
	}

	@GET
	@Path(value = "/kiemTraDangNhap/{taikhoan}/{matkhau}")
	@Produces(MediaType.APPLICATION_JSON)
	public String kiemTraDangNhap(@PathParam(value = "taikhoan") String taikhoan,
			@PathParam(value = "matkhau") String matkhau) {
		Gson gs = new Gson();
		UserEntity user = UserServiceImpl.getNewUser().selectByDangNhap(taikhoan, matkhau);
		user.setListUserRole(null);
		String data = gs.toJson(user);
		return data;
	}

	// Api thêm
	@POST
	@Path(value = "/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public String insertUser(String data) {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		UserEntity user = gs.fromJson(data, UserEntity.class);
		NotificationEntity tb = new NotificationEntity();
		Boolean themTc = UserServiceImpl.getNewUser().insert(user);
		if (themTc) {
			tb.setMacode(user.getId());
			tb.setText("Thành công!");
		} else {
			tb.setMacode(0);
			tb.setText("Thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;

	}

	@POST
	@Path(value = "/update")
	@Produces(MediaType.APPLICATION_JSON)
	public String update(String data) {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		UserEntity user = gs.fromJson(data, UserEntity.class);
		NotificationEntity tb = new NotificationEntity();
		Boolean themTc = UserServiceImpl.getNewUser().update(user);
		if (themTc) {
			tb.setMacode(user.getId());
			tb.setText("Thành công!");
		} else {
			tb.setMacode(0);
			tb.setText("Thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;

	}

	@GET
	@Path(value = "/search/{idla}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchUser(@PathParam(value = "idla") int idla) {
		Gson gs = new Gson();
		UserEntity user = UserServiceImpl.getNewUser().selectById(idla);
		user.setListUserRole(null);
		String data = gs.toJson(user);
		return data;
	}

	@GET
	@Path(value = "/search-email/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchEmail(@PathParam(value = "email") String email) {
		Gson gs = new Gson();
		UserEntity user = UserServiceImpl.getNewUser().findByEmail(email);
		if (user != null) {
			user.setListUserRole(null);
		}
		String data = gs.toJson(user);
		return data;
	}
}
