package com.fashion.jersey.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fashion.DAO.HoaDonChiTietIml;
import com.fashion.entity.HoaDonChiTiet;
import com.fashion.entity.ThongBao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path(value = "/Ct-hoa-don")
public class HoaDonChiTietApi {

	@GET
	@Path(value = "/list")
	@Produces(MediaType.APPLICATION_JSON)
	public String listCtHoaDon() {
		Gson gs = new Gson();
		List<HoaDonChiTiet> list = HoaDonChiTietIml.getNewHoaDonChiTiet().selectAll();
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
	@Path(value = "/them-hoadon-ct")
	@Produces(MediaType.APPLICATION_JSON)
	public String themHoaDonCt(String dulieu) {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		HoaDonChiTiet hdct = gs.fromJson(dulieu, HoaDonChiTiet.class);
		Boolean themtc = HoaDonChiTietIml.getNewHoaDonChiTiet().insert(hdct);
		ThongBao tb = new ThongBao();
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
		List<HoaDonChiTiet> list = HoaDonChiTietIml.getNewHoaDonChiTiet().selectByIdHd(idla);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setIdHoaDon(list.get(i).getId_hoadon().getId());
			list.get(i).setIdSanPhamCt(list.get(i).getId_sp().getId());
			list.get(i).setId_hoadon(null);
			list.get(i).setId_sp(null);
		}
	   String data = gs.toJson(list);
	   return data;
	}
}
