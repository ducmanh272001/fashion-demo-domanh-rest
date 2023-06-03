package com.fashion.jersey.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fashion.entity.CalculateEntity;
import com.fashion.entity.ColorEntity;
import com.fashion.entity.CustomerEntity;
import com.fashion.entity.NotificationEntity;
import com.fashion.entity.PaymentEntity;
import com.fashion.entity.ProductEntity;
import com.fashion.payment.Config;
import com.fashion.service.branch.BranchServiceImpl;
import com.fashion.service.calculate.CalculateServiceImpl;
import com.fashion.service.color.ColorServiceImpl;
import com.fashion.service.customer.CustomerServiceImpl;
import com.fashion.service.payment.PaymentServiceImpl;
import com.fashion.service.product.ProductServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.val;

@Path(value = "/api/v1/payment")
public class PaymentController {

	@GET
	@Path(value = "/create-payment")
	@Produces(MediaType.APPLICATION_JSON)
	public String createPayment(@Context HttpServletRequest req, @Context HttpServletResponse resp)
			throws ServletException, IOException {
		String vnp_Version = "2.1.0";
		String vnp_Command = "pay";
//        String orderType = req.getParameter("ordertype");

		String floatReplaceLong = req.getParameter("amount").replace(".", "");

		long amount = Integer.parseInt(floatReplaceLong) * 10;

		// Ghi hóa đơn ở đây
		String vnp_TxnRef = Config.getRandomNumber(8);

		// Lưu id Hóa đơn
//		String vnp_IpAddr = req.getParameter("idHoaDon");
		String vnp_TmnCode = Config.vnp_TmnCode;// Mã fig cứng

		Map<String, String> vnp_Params = new HashMap<>();
		vnp_Params.put("vnp_Version", vnp_Version);
		vnp_Params.put("vnp_Command", vnp_Command);
		vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
		vnp_Params.put("vnp_Amount", String.valueOf(amount));
		vnp_Params.put("vnp_CurrCode", "VND");
		vnp_Params.put("vnp_BankCode", "NCB");
		vnp_Params.put("vnp_Locale", "vn");
		vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
		vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
		vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);// Mã fig cứng
//		vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

		Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String vnp_CreateDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

		cld.add(Calendar.MINUTE, 7900);
		String vnp_ExpireDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

		List fieldNames = new ArrayList(vnp_Params.keySet());
		Collections.sort(fieldNames);
		StringBuilder hashData = new StringBuilder();
		StringBuilder query = new StringBuilder();
		Iterator itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) vnp_Params.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				// Build hash data
				hashData.append(fieldName);
				hashData.append('=');
				hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				// Build query
				query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
				query.append('=');
				query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				if (itr.hasNext()) {
					query.append('&');
					hashData.append('&');
				}
			}
		}
		String queryUrl = query.toString();
		String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
		queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
		String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;

		return paymentUrl;
	}
	
	
	
	
	@POST
	@Path(value = "/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public String insert(String data) {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		PaymentEntity hs = gs.fromJson(data, PaymentEntity.class);
		NotificationEntity tb = new NotificationEntity();
		Boolean saveOk = PaymentServiceImpl.getNewPayment().create(hs);
		if (saveOk) {
			tb.setMacode(1);
			tb.setText("Thêm thành công");
		} else {
			tb.setMacode(0);
			tb.setText("Thêm thất bại");
		}
		String trave = gs.toJson(tb);
		return trave;
	}


	/// Payment

	@GET
	@Path("/list/{pageNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listpPayment(@PathParam(value = "pageNumber")Integer pageNumber) {
		List<PaymentEntity> list = PaymentServiceImpl.getNewPayment().list(pageNumber);
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String dulieu = gs.toJson(list);
		return dulieu;
	}
	
	
	@GET
	@Path(value = "/count")
	@Produces(MediaType.APPLICATION_JSON)
	public String count() {
		Long soluong = PaymentServiceImpl.getNewPayment().count();
		Gson gs = new Gson();
		String data = gs.toJson(soluong);
		return data;
	}

}
