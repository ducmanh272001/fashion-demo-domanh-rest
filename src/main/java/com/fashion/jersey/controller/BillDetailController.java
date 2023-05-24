package com.fashion.jersey.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fashion.entity.BillDetailEntity;
import com.fashion.entity.NotificationEntity;
import com.fashion.service.bill.BillServiceImpl;
import com.fashion.service.billdetail.BillDetailServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path(value = "/api/v1/bill-detail")
public class BillDetailController {

	@GET
	@Path(value = "/list")
	@Produces(MediaType.APPLICATION_JSON)
	public String listCtHoaDon() {
		Gson gs = new Gson();
		List<BillDetailEntity> list = BillDetailServiceImpl.getNewBillDetailEntity().selectAll();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setIdHoaDon(list.get(i).getId_hoadon().getId());
			list.get(i).setIdSanPhamCt(list.get(i).getId_sp().getId());
			list.get(i).setId_hoadon(null);
			list.get(i).setId_sp(null);
		}
	   String data = gs.toJson(list);
	   return data;
	}
	
	//Thêm sản phẩm
	@POST
	@Path(value = "/")
	@Produces(MediaType.APPLICATION_JSON)
	public String themHoaDonCt(String dulieu) {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BillDetailEntity hdct = gs.fromJson(dulieu, BillDetailEntity.class);
		Boolean themtc = BillDetailServiceImpl.getNewBillDetailEntity().insert(hdct);
		NotificationEntity tb = new NotificationEntity();
		if(themtc) {
			tb.setMacode(hdct.getId());
			tb.setText("Thêm thành công");
		}else {
			tb.setMacode(0);
			tb.setText("Thêm dữ liệu thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;
	}
	
	
	@GET
	@Path(value = "/search-id-hoadon/{idhd}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchIdHoaDon(@PathParam(value = "idhd")int idla) {
		
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		List<BillDetailEntity> list = BillDetailServiceImpl.getNewBillDetailEntity().selectByIdHd(idla);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setIdHoaDon(list.get(i).getId_hoadon().getId());
			list.get(i).setIdSanPhamCt(list.get(i).getId_sp().getId());
			list.get(i).setId_hoadon(null);
			list.get(i).setId_sp(null);
		}
	   String data = gs.toJson(list);
	   return data;
	}
	
	
	
	@POST
	@Path(value = "/delete-hoa-don-ct/{idxoa}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteHoaDon(@PathParam(value = "idxoa")Integer idxoa) {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		NotificationEntity tb = new NotificationEntity();
		Boolean xoatc = BillDetailServiceImpl.getNewBillDetailEntity().deleteAllByBillId(idxoa);
		if(xoatc) {
			tb.setMacode(1);
			tb.setText("Xóa hóa đơn thành công");
		}else {
			tb.setMacode(0);
			tb.setText("Xóa hóa đơn không thành công");
		}
		String trave = gs.toJson(tb);
		return trave;
	}
}
