package com.fashion.jersey.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fashion.DAO.HoaDonImpl;
import com.fashion.entity.HoaDon;
import com.fashion.entity.ThongBao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path(value = "/HoaDon")
public class HoaDonApi {

	@GET
	@Path(value = "/list")
	@Produces(MediaType.APPLICATION_JSON)
	public String dulieuHoaDon() {
		List<HoaDon> list = HoaDonImpl.getNewHoaDon().selectAll();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setListHoaDonCt(null);
			list.get(i).setMakh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String dulieu = gs.toJson(list);
		return dulieu;
	}
	
	//Thêm dữ liệu
	@POST
	@Path(value = "/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public String themHoaDon(String hoadon) {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		HoaDon hd = gs.fromJson(hoadon, HoaDon.class);
		Boolean themtc = HoaDonImpl.getNewHoaDon().insert(hd);
		ThongBao tb = new ThongBao();
		if(themtc) {
			tb.setMacode(hd.getId());
			tb.setText("Thêm thành công");
		}else {
			tb.setMacode(0);
			tb.setText("Thêm thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;
	}
	
	//Tìm kiếm để nối lại
	@GET
	@Path(value = "/search-id-hd/{idhoadon}")
	@Produces(MediaType.APPLICATION_JSON)
	public String timKiemHoaDon(@PathParam(value = "idhoadon")int idhoadon) {
		HoaDon hd = HoaDonImpl.getNewHoaDon().selectById(idhoadon);
		hd.setIdmakh(hd.getMakh().getId());
		hd.setListHoaDonCt(null);
		hd.setMakh(null);
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(hd);
		return data;
	}
	
	@GET
	@Path(value = "/search-makh/{idla}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchKhachHang(@PathParam(value = "idla")int idla) {
		List<HoaDon> lsthd = HoaDonImpl.getNewHoaDon().selectByIdKh(idla);
		for (int i = 0; i < lsthd.size(); i++) {
			lsthd.get(i).setListHoaDonCt(null);
			lsthd.get(i).setMakh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String data = gs.toJson(lsthd);
		return data;
	}
	
	@GET
	@Path(value = "/count-hoa-don")
	@Produces(MediaType.APPLICATION_JSON)
	public String CountHoaDon() {
		Long sohoadon = HoaDonImpl.getNewHoaDon().selectCountHoaDon();
		Gson gs = new Gson();
		String data = gs.toJson(sohoadon);
		return data;
	}
	
	//Sửa hóa đơn 
	@POST
	@Path(value = "/update")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateHoaDon(String data) {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		HoaDon hd = gs.fromJson(data, HoaDon.class);
		ThongBao tb = new ThongBao();
		Boolean suatc = HoaDonImpl.getNewHoaDon().update(hd);
		if(suatc) {
			tb.setMacode(1);
			tb.setText("Sửa hóa đơn thành công");
		}else {
			tb.setMacode(0);
			tb.setText("Sửa hóa đơn không thành công");
		}
		String trave = gs.toJson(tb);
		return trave;
	}
	//xÓA HÓA ĐƠN
	@POST
	@Path(value = "/delete-hoa-don/{idxoa}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteHoaDon(@PathParam(value = "idxoa")int idxoa) {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		ThongBao tb = new ThongBao();
		Boolean xoatc = HoaDonImpl.getNewHoaDon().delete(idxoa);
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
	
	//Phân trang hóa đơn
	@GET
	@Path(value = "/phanTrangHoaDon/{sotrang}")
	@Produces(MediaType.APPLICATION_JSON)
	public String phanTrangHoaDon(@PathParam(value = "sotrang") int sotrang) {
		List<HoaDon> list = HoaDonImpl.getNewHoaDon().phanTrangHoaDon(sotrang);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setListHoaDonCt(null);
			list.get(i).setMakh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String dulieu = gs.toJson(list);
		return dulieu;
	}

}
