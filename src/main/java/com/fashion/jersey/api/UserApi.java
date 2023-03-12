package com.fashion.jersey.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fashion.DAO.UserImpl;
import com.fashion.entity.ThongBao;
import com.fashion.entity.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path(value = "/User")
public class UserApi {

	@GET
	@Path(value = "")
	@Produces(MediaType.APPLICATION_JSON)
	public String listUser() {
		List<User> list = UserImpl.getNewUser().selectAll();
		for (User user : list) {
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
		User user = UserImpl.getNewUser().selectByDangNhap(taikhoan, matkhau);
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
		User user = gs.fromJson(data, User.class);
		ThongBao tb = new ThongBao();
		Boolean themTc = UserImpl.getNewUser().insert(user);
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
		User user = UserImpl.getNewUser().selectById(idla);
		user.setListUserRole(null);
		String data = gs.toJson(user);
		return data;
	}
}
