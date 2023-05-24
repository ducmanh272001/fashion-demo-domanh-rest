package com.fashion.jersey.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fashion.entity.BranchEntity;
import com.fashion.service.branch.BranchServiceImpl;
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
}
