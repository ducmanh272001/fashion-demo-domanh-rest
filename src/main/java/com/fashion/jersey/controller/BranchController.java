package com.fashion.jersey.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fashion.entity.BranchEntity;
import com.fashion.entity.HotProductEntity;
import com.fashion.entity.NotificationEntity;
import com.fashion.entity.TypeProductEntity;
import com.fashion.service.branch.BranchServiceImpl;
import com.fashion.service.hotproduct.HotProductServiceImpl;
import com.fashion.service.typeproduct.TypeProductServiceImpl;
import com.google.gson.Gson;

@Path(value = "/api/v1/branch")
public class BranchController {

	@GET
	@Path(value = "/")
	@Produces(MediaType.APPLICATION_JSON)
	public String listNhanHieu() {
		//Lấy ra danh sách list nhan hiệu
		List<BranchEntity> list = BranchServiceImpl.getNewBranchEntity().selectAll();
		for (BranchEntity nhanHieu : list) {
			nhanHieu.setListSanPham(null);
		}
		Gson gs = new Gson();
		String data = gs.toJson(list);
		return data;
	}
	
	

	@GET
	@Path(value = "/search/{idla}")
	public String findBrand(@PathParam(value = "idla")int idla) {
		// Trả về 1 cái String mà giống mvc thôi
		Gson gs = new Gson();
		BranchEntity lsp = BranchServiceImpl.getNewBranchEntity().selectById(idla);
		lsp.setListSanPham(null);
		String data = gs.toJson(lsp);
		return data;
	}
	
	@GET
	@Path(value = "/phan-trang/{idla}")
	public String phanTrang(@PathParam(value = "idla") int idla) {
		// Trả về 1 cái String mà giống mvc thôi
		Gson gs = new Gson();
		List<BranchEntity> list = BranchServiceImpl.getNewBranchEntity().phanTrang(idla);
		for (BranchEntity loaiSanPham : list) {
			loaiSanPham.setListSanPham(null);
		}
		String data = gs.toJson(list);
		return data;
	}

	@POST
	@Path(value = "/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public String save(String nhanhHieu) {
		Gson gs = new Gson();
		BranchEntity hd = gs.fromJson(nhanhHieu, BranchEntity.class);
		Boolean themtc = BranchServiceImpl.getNewBranchEntity().insert(hd);
		NotificationEntity tb = new NotificationEntity();
		if(themtc) {
			tb.setText("Thêm thành công");
		}else {
			tb.setText("Thêm thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;
	}
	
	
	// Xóa
		@POST
		@Path(value = "/delete/{idxoa}")
		public String deleteDanhMuc(@PathParam(value = "idxoa") int idxoa) {
			Gson gs = new Gson();
			NotificationEntity tb = new NotificationEntity();
			Boolean xoatc = BranchServiceImpl.getNewBranchEntity().delete(idxoa);
			if (xoatc) {
				tb.setMacode(1);
				tb.setText("Xóa nhãn hiệu thành công");
			} else {
				tb.setMacode(0);
				tb.setText("Xóa nhãn hiệu không thành công");
			}
			String trave = gs.toJson(tb);
			return trave;
		}
	
	

	@POST
	@Path(value = "/update")
	public String update(String data) {
		Gson gs = new Gson();
		BranchEntity lsp = gs.fromJson(data, BranchEntity.class);
		Boolean themtc = BranchServiceImpl.getNewBranchEntity().update(lsp);
		NotificationEntity tb = new NotificationEntity();
		if (themtc) {
			tb.setMacode(1);
			tb.setText("Sửa nhãn hiệu thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Sửa nhãn hiệu thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;
	}
	
	@GET
	@Path(value = "/count")
	public String countThuongHieu() {
		Long soluong = BranchServiceImpl.getNewBranchEntity().selectCount();
		Gson gs = new Gson();
		String data = gs.toJson(soluong);
		return data;
	}
}
