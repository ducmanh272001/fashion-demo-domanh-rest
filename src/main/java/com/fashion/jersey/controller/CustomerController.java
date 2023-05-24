package com.fashion.jersey.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fashion.entity.CustomerEntity;
import com.fashion.entity.NotificationEntity;
import com.fashion.service.customer.CustomerServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/api/v1/customer")
public class CustomerController {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String listKhachHang() {
		List<CustomerEntity> list = CustomerServiceImpl.newCustomerEntity().selectAll();
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setListHoaDon(null);
		}
		String dulieu = gs.toJson(list);
		return dulieu;
	}

	// Thêm khách hàng
	@POST
	@Path(value = "/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public String themKhachHang(String dulieu) {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		CustomerEntity kh = gs.fromJson(dulieu, CustomerEntity.class);
		Boolean themtc = CustomerServiceImpl.newCustomerEntity().insert(kh);
		NotificationEntity tb = new NotificationEntity();
		if (themtc) {
			tb.setMacode(kh.getId());
			tb.setText("Thêm dữ liệu thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Thêm dữ liệu thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;
	}

	// Sửa khách hàng
	@POST
	@Path(value = "/update")
	@Produces(MediaType.APPLICATION_JSON)
	public String SuaKhachHang(String dulieu) {
		Gson gs = new Gson();
		CustomerEntity kh = gs.fromJson(dulieu, CustomerEntity.class);
		Boolean themtc = CustomerServiceImpl.newCustomerEntity().update(kh);
		NotificationEntity tb = new NotificationEntity();
		if (themtc) {
			tb.setMacode(1);
			tb.setText("Sửa dữ liệu thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Sửa dữ liệu thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;
	}

	// Tìm khách hàng
	@GET
	@Path(value = "/search/{idla}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchIdSanPham(@PathParam(value = "idla") int idok) {
		CustomerEntity kh = CustomerServiceImpl.newCustomerEntity().selectById(idok);
		kh.setListHoaDon(null);
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(kh);
		return data;
	}

	// Tìm số lượng khách hàng
	@GET
	@Path(value = "/count-khach-hang")
	@Produces(MediaType.APPLICATION_JSON)
	public String CountKhachHang() {
		Long soluong = CustomerServiceImpl.newCustomerEntity().selectCountKh();
		Gson gs = new Gson();
		String dulieu = gs.toJson(soluong);
		return dulieu;
	}

	// Xóa khách hàng
	@POST
	@Path(value = "/delete/{idxoa}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteKhachHang(@PathParam(value = "idxoa") int idla) {
		Boolean xoatc = CustomerServiceImpl.newCustomerEntity().delete(idla);
		NotificationEntity tb = new NotificationEntity();
		if (xoatc) {
			tb.setMacode(1);
			tb.setText("Xóa thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Xóa không thành công");
		}
		Gson gs = new Gson();
		String data = gs.toJson(tb);
		return data;
	}

	// pHÂN Trang khách hàng
	@GET
	@Path("/phan-trang-khach-hang/{sotrang}")
	@Produces(MediaType.APPLICATION_JSON)
	public String phanTrangKhachHang(@PathParam(value = "sotrang") int sotrang) {
		List<CustomerEntity> list = CustomerServiceImpl.newCustomerEntity().phanTrangCustomerEntity(sotrang);
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setListHoaDon(null);
		}
		String dulieu = gs.toJson(list);
		return dulieu;
	}
}
