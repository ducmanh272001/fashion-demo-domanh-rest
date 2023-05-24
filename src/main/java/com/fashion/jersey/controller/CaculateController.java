package com.fashion.jersey.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fashion.entity.CalculateEntity;
import com.fashion.entity.CustomerEntity;
import com.fashion.entity.NotificationEntity;
import com.fashion.service.calculate.CalculateService;
import com.fashion.service.calculate.CalculateServiceImpl;
import com.fashion.service.customer.CustomerServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path(value = "/api/v1/calculate")
public class CaculateController {
	
	
	// Thêm khách hàng
	@POST
	@Path(value = "/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public String save(String dulieu) {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		CalculateEntity calculateEntity = gs.fromJson(dulieu, CalculateEntity.class);
		Boolean themtc = CalculateServiceImpl.getCalculateServiceImpl().save(calculateEntity);
		NotificationEntity tb = new NotificationEntity();
		if (themtc) {
			tb.setText("Thêm dữ liệu thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Thêm dữ liệu thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;
	}
	
	
	// Xóa khách hàng
	@POST
	@Path(value = "/delete/{idxoa}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteKhachHang(@PathParam(value = "idxoa") int idla) {
		Boolean xoatc = CalculateServiceImpl.getCalculateServiceImpl().delete(idla);
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
	
	
	@POST
	@Path(value = "/delete-all")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteAll() {
	  Boolean deletSuccess = CalculateServiceImpl.getCalculateServiceImpl().deleteAll();
	  NotificationEntity tb = new NotificationEntity();
		if (deletSuccess) {
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
	
	@POST
	@Path(value = "/delete-productId/{idxoa}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteAllProductId(@PathParam(value = "idxoa") int idla) {
		Boolean xoatc = CalculateServiceImpl.getCalculateServiceImpl().deleteAllFindProductId(idla);
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
	
	
	@GET
	@Path(value = "/search/{productId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findAllByProductId(@PathParam(value = "productId") int idok) {
		List<CalculateEntity> kh = CalculateServiceImpl.getCalculateServiceImpl().findAllByProductId(idok);	
		Gson gs = new Gson();
		String data = gs.toJson(kh);
		return data;
	}
	
	@GET
	@Path(value = "/search-quantity/{productId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findQuantity(@PathParam(value = "productId") Integer idok) {
		long idOk = CalculateServiceImpl.getCalculateServiceImpl().getQuantityFromProductId(idok);
		Gson gs = new Gson();
		String data = gs.toJson(idOk);
		return data;
	}
		


}
