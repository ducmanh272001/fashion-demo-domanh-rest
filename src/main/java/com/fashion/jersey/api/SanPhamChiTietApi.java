package com.fashion.jersey.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fashion.DAO.SanPhamChiTietImpl;
import com.fashion.entity.SanPhamChiTiet;
import com.fashion.entity.ThongBao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path(value = "/san-pham-detail")
public class SanPhamChiTietApi {

	@GET
	@Path(value = "/list")
	@Produces(MediaType.APPLICATION_JSON)
	public String sanPhamDetail() {
		//Lấy ra list sản phẩm chi tiết
		List<SanPhamChiTiet> lspct = SanPhamChiTietImpl.getNewSanPhamChiTiet().selectAll();
		for (int i = 0; i < lspct.size(); i++) {
			lspct.get(i).setSanpham_name(lspct.get(i).getMact().getName());
			lspct.get(i).setMausac_name(lspct.get(i).getIdms().getName());
			lspct.get(i).setKichco_name(lspct.get(i).getIdkc().getName());
			lspct.get(i).setListHoaDonCt(null);
			lspct.get(i).setIdms(null);
			lspct.get(i).setMact(null);
			lspct.get(i).setIdkc(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String dulieu = gs.toJson(lspct);
		return dulieu;
	}
	
	
	//Thêm sản phẩm chi tiết
	@POST
	@Path(value = "/them-sp-ct")
	@Produces(MediaType.APPLICATION_JSON)
	public String themSanPhamCt(String dulieu) {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		SanPhamChiTiet spct = gs.fromJson(dulieu, SanPhamChiTiet.class);
		Boolean themtc = SanPhamChiTietImpl.getNewSanPhamChiTiet().insert(spct);
		ThongBao tb = new ThongBao();
		if(themtc) {
			tb.setMacode(spct.getId());
			tb.setText("Thêm thành công!");
		}else {
			tb.setMacode(1);
			tb.setText("Thêm thất bại!");
		}
		String trave = gs.toJson(tb);
		return trave;
	}
	
	
	//Tìm sản phẩm chi tiết
	@GET
	@Path(value = "/search-sp-detail/{sptim}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchSanPham(@PathParam(value = "sptim")int idla) {
		SanPhamChiTiet spok = SanPhamChiTietImpl.getNewSanPhamChiTiet().selectById(idla);
		spok.setKichco_name(spok.getIdkc().getName());
		spok.setMausac_name(spok.getIdms().getName());
		spok.setSanpham_name(spok.getMact().getName());
		spok.setListHoaDonCt(null);
		spok.setMact(null);
		spok.setIdms(null);
		spok.setIdkc(null);
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(spok);
		return data;
	}
}
