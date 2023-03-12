package com.fashion.jersey.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataParam;

import com.fashion.DAO.TinTucIml;
import com.fashion.entity.ThongBao;
import com.fashion.entity.TinTuc;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path(value = "/Tintuc")
public class TinTucApi {

	@Context
	ServletContext servletcontext;

	@POST
	@Path(value = "/insert")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	@Produces(MediaType.APPLICATION_JSON)
	public String insertSanPham(@FormDataParam(value = "dulieu") String data,
			@FormDataParam(value = "mf") InputStream fileLuu) throws IOException {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		TinTuc hs = gs.fromJson(data, TinTuc.class);
		if (fileLuu != null) {
			// Lấy ra tên file ảnh
			String tenfile = hs.getImg();
			// Lấy ra dường dẫn gốc
			String ddgoc = servletcontext.getRealPath("/public/img");
			/// Lưu ý là cái separator là cái dấu xoạc
			File file = new File(ddgoc + File.separator + tenfile);
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
		ThongBao tb = new ThongBao();
		Boolean themTc = TinTucIml.getNewTinTuc().insert(hs);
		if (themTc) {
			tb.setMacode(1);
			tb.setText("Thêm thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Thêm thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;
	}

	// Update tin tức
	@POST
	@Path(value = "/update")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateTinTuc(@FormDataParam(value = "dulieu") String tintuc,
			@FormDataParam(value = "mf") InputStream is) throws IOException {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		TinTuc tintucok = gs.fromJson(tintuc, TinTuc.class);
		if (is != null) {
			String tenImg = tintucok.getImg();
			String ddgoc = servletcontext.getRealPath("/public/img");
			File file = new File(ddgoc + File.separator + tenImg);
			// Lưu lại
			FileOutputStream fos = new FileOutputStream(file);
			int docdulieu;
			byte doc[] = new byte[1024];
			while ((docdulieu = is.read(doc)) != -1) {
				fos.write(doc, 0, docdulieu);
			}
			fos.close();
			is.close();
		} else {
			System.out.println("Khoog có mutilpart file !");
		}
		Boolean suaTc = TinTucIml.getNewTinTuc().update(tintucok);
		ThongBao tb = new ThongBao();
		if (suaTc) {
			tb.setMacode(1);
			tb.setText("Sửa sản phẩm thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Sửa sản phẩm không thành công");
		}
		String trave = gs.toJson(tb);
		return trave;
	}

	@POST
	@Path(value = "/delete/{idxoa}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteTinTuc(@PathParam(value = "idxoa") int idxoa, @Context HttpServletRequest request) {
		// Xóa cả ở trong file
		TinTuc timtt = TinTucIml.getNewTinTuc().selectById(idxoa);
		String img = timtt.getImg();
		String ddgoc = request.getServletContext().getRealPath("/public/img");
		File file = new File(ddgoc + File.separator + img);
		Boolean xoaTc = file.delete();
		if (xoaTc) {
			System.out.println("Đã xóa file img thành công");
		} else {
			System.out.println("Xóa ko thành công");
		}
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Boolean xoatc = TinTucIml.getNewTinTuc().delete(idxoa);
		ThongBao tb = new ThongBao();
		if (xoatc) {
			tb.setMacode(1);
			tb.setText("Xóa thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Xóa không thành công");
		}
		String trave = gs.toJson(tb);
		return trave;
	}

	// List tin tức
	@GET
	@Path(value = "/list")
	@Produces(MediaType.APPLICATION_JSON)
	public String ListTinTuc(String data) {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		List<TinTuc> list = TinTucIml.getNewTinTuc().selectAll();
		String trave = gs.toJson(list);
		return trave;
	}

	@GET
	@Path(value = "/search-id/{idtim}")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchTinTuc(@PathParam(value = "idtim") int idtim) {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		TinTuc tintuc = TinTucIml.getNewTinTuc().selectById(idtim);
		String trave = gs.toJson(tintuc);
		return trave;
	}

	@GET
	@Path(value = "/count")
	@Produces(MediaType.APPLICATION_JSON)
	public String countTinTuc() {
		Long soluong = TinTucIml.getNewTinTuc().countTinTuc();
		Gson gs = new Gson();
		String trave = gs.toJson(soluong);
		return trave;
	}
}
