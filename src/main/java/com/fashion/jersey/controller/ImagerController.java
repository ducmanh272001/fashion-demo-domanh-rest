package com.fashion.jersey.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataParam;

import com.fashion.entity.ImagerEntity;
import com.fashion.entity.NotificationEntity;
import com.fashion.service.imager.ImagerServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path(value = "/api/v1/imager")
public class ImagerController {

	@Context
	ServletContext servletcontext;

	@GET
	@Path(value = "/list")
	@Produces(MediaType.APPLICATION_JSON)
	public String listHinhAnh() {
		List<ImagerEntity> list = ImagerServiceImpl.getNewHinhAnh().selectAll();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setIdsp(list.get(i).getIdhinhanh().getId());
			list.get(i).setIdhinhanh(null);
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String dulieu = gs.toJson(list);
		return dulieu;
	}

	// Thêm dữ liệu
	@POST
	@Path(value = "/insert")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	@Produces(MediaType.APPLICATION_JSON)
	public String themHinhAnh(@FormDataParam(value = "dulieu") String data,
			@FormDataParam(value = "mf") InputStream fileLuu) throws IOException {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		ImagerEntity hs = gs.fromJson(data, ImagerEntity.class);
		String tenfile = hs.getName();
		if (fileLuu != null) {
			String ddgoc = servletcontext.getRealPath("/public/img");
			/// Lưu ý là cái separator là cái dấu xoạc
			File file = new File(ddgoc + File.separator + tenfile);
			System.out.println(file);
			FileOutputStream fos = new FileOutputStream(file);
			byte buff[] = new byte[1024];
			int docdl;
			while ((docdl = fileLuu.read(buff)) != -1) {
				fos.write(buff, 0, docdl);
			}
			fos.close();
			fileLuu.close();

		} else {
			System.out.println("Không có multipartfile");
		}
		// Bỏ dữ liệu vào haisanImpl;
		NotificationEntity tb = new NotificationEntity();
		Boolean themha = ImagerServiceImpl.getNewHinhAnh().insert(hs);
		if (themha) {
			tb.setMacode(1);
			tb.setText("Thêm thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Thêm thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;
	}

	// Thêm dữ liệu
	@POST
	@Path(value = "/update")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	@Produces(MediaType.APPLICATION_JSON)
	public String updateHinhAnh(@FormDataParam(value = "dulieu") String data,
			@FormDataParam(value = "mf") InputStream fileLuu) throws IOException {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		ImagerEntity hs = gs.fromJson(data, ImagerEntity.class);
		String tenfile = hs.getName();
		if (fileLuu != null) {
			String ddgoc = servletcontext.getRealPath("/public/img");
			/// Lưu ý là cái separator là cái dấu xoạc
			File file = new File(ddgoc + File.separator + tenfile);
			System.out.println(file);
			FileOutputStream fos = new FileOutputStream(file);
			byte buff[] = new byte[1024];
			int docdl;
			while ((docdl = fileLuu.read(buff)) != -1) {
				fos.write(buff, 0, docdl);
			}
			fos.close();
			fileLuu.close();

		} else {
			System.out.println("Không có multipartfile");
		}
		// Bỏ dữ liệu vào haisanImpl;
		NotificationEntity tb = new NotificationEntity();
		Boolean themha = ImagerServiceImpl.getNewHinhAnh().update(hs);
		if (themha) {
			tb.setMacode(1);
			tb.setText("Sửa thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Sửa thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;
	}

	@GET
	@Path(value = "/list-tim-idsp/{idsp}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listHinhAnh(@PathParam(value = "idsp") int idla) {
		List<ImagerEntity> list = ImagerServiceImpl.getNewHinhAnh().selectByIdSanPham(idla);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setIdsp(list.get(i).getIdhinhanh().getId());
			list.get(i).setIdhinhanh(null);
		}
		Gson gs = new Gson();
		String dulieu = gs.toJson(list);
		return dulieu;
	}

	// Lấy 2 hình ảnh
	@GET
	@Path(value = "/listTwoHinhAnh")
	@Produces(MediaType.APPLICATION_JSON)
	public String listTwoHinhAnh() {
		List<ImagerEntity> list = ImagerServiceImpl.getNewHinhAnh().selectHinhAnh2();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setIdsp(list.get(i).getIdhinhanh().getId());
			list.get(i).setIdhinhanh(null);
		}
		Gson gs = new Gson();
		String dulieu = gs.toJson(list);
		return dulieu;
	}
}
