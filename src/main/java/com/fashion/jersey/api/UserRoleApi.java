package com.fashion.jersey.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fashion.DAO.UserRoleImpl;
import com.fashion.entity.ThongBao;
import com.fashion.entity.UserRole;
import com.google.gson.Gson;

@Path(value = "/user-role")
public class UserRoleApi {

	
	@GET
	@Path(value = "/list")
	@Produces(MediaType.APPLICATION_JSON)
	public String listUserRole() {
		Gson gs = new Gson();
		List<UserRole> list = UserRoleImpl.getNewUserRole().selectAll();
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
		UserRole user = UserRoleImpl.getNewUserRole().selectById(idla);
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
		UserRole user = UserRoleImpl.getNewUserRole().selectByNguoiDung(idla);
		user.setName_Role_ID(user.getRoleId().getName());
		user.setRoleId(null);
		user.setUserId(null);
		String data = gs.toJson(user);
		return data;
	}
	
	
	@POST
	@Path(value = "/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public String insertUserRole(String data) {
		Gson gs = new Gson();
		UserRole user = gs.fromJson(data, UserRole.class);
		ThongBao tb = new ThongBao();
		Boolean themtc = UserRoleImpl.getNewUserRole().insert(user);
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
