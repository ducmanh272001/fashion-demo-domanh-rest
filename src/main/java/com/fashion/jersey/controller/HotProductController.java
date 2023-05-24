package com.fashion.jersey.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fashion.entity.HotProductEntity;
import com.fashion.entity.NotificationEntity;
import com.fashion.entity.ProductEntity;
import com.fashion.service.hotproduct.HotProductServiceImpl;
import com.fashion.service.product.ProductServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/api/v1/hot-product")
public class HotProductController {
	
	
	@GET
	@Path(value = "/")
	@Produces(MediaType.APPLICATION_JSON)
	public String list() {
		//Lấy ra danh sách list nhan hiệu
		List<HotProductEntity> list = HotProductServiceImpl.newHotProduct().selectAll();
		Gson gs = new Gson();
		String data = gs.toJson(list);
		return data;
	}
	
	
	@GET
	@Path(value = "/listLongIdSP")
	@Produces(MediaType.APPLICATION_JSON)
	public String listLongIdSp() {
		//Lấy ra danh sách list nhan hiệu
		List<Long> list = HotProductServiceImpl.newHotProduct().idSpNews();
		Gson gs = new Gson();
		String data = gs.toJson(list);
		return data;
	}

	
	
	
	@POST
	@Path(value = "/")
	@Produces(MediaType.APPLICATION_JSON)
	public String save(String hotProduct) {
		Gson gs = new Gson();
		HotProductEntity hd = gs.fromJson(hotProduct, HotProductEntity.class);
		Boolean themtc = HotProductServiceImpl.newHotProduct().insert(hd);
		NotificationEntity tb = new NotificationEntity();
		if(themtc) {
			
			long longValue = hd.getId();

			int intValue = (int)longValue;
		
			tb.setMacode(intValue);
			tb.setText("Thêm thành công");
		}else {
			tb.setMacode(0);
			tb.setText("Thêm thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;
	}
	
	

	
	
	
	@POST
	@Path(value = "/update")
	@Produces(MediaType.APPLICATION_JSON)
	public String update(String data) {
		Gson gs = new Gson();
		HotProductEntity hd = gs.fromJson(data, HotProductEntity.class);
		NotificationEntity tb = new NotificationEntity();
		Boolean suatc = HotProductServiceImpl.newHotProduct().update(hd);
		if(suatc) {
			tb.setMacode(1);
			tb.setText("Sửa thành công");
		}else {
			tb.setMacode(0);
			tb.setText("Sửa không thành công");
		}
		String trave = gs.toJson(tb);
		return trave;
	}
	//xÓA HÓA ĐƠN
	@POST
	@Path(value = "/delete/{idxoa}")
	@Produces(MediaType.APPLICATION_JSON)
	public String delete(@PathParam(value = "idxoa")int idxoa) {
		Gson gs = new Gson();
		NotificationEntity tb = new NotificationEntity();
		Boolean xoatc = HotProductServiceImpl.newHotProduct().delete(idxoa);
		if(xoatc) {
			tb.setMacode(1);
			tb.setText("Xóa thành công");
		}else {
			tb.setMacode(0);
			tb.setText("Xóa không thành công");
		}
		String trave = gs.toJson(tb);
		return trave;
	}
	
	
	@GET
	@Path(value = "/find-idsp/{idla}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchIdSanPham(@PathParam(value = "idla") int idok) {
		HotProductEntity sp = HotProductServiceImpl.newHotProduct().findAllByIdsp(idok);
		Gson gs = new Gson();				
		String data = gs.toJson(sp);
		return data;
	}

}
