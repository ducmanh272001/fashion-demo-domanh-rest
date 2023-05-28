package com.fashion.jersey.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fashion.entity.CalculateEntity;
import com.fashion.payment.Config;
import com.fashion.service.calculate.CalculateServiceImpl;
import com.google.gson.Gson;

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
		
		
        long amount = Integer.parseInt(floatReplaceLong)*100;
//        String bankCode = req.getParameter("bankCode");
//		long amount = 1000000;
		//Ghi hóa đơn ở đây 
		String vnp_TxnRef = Config.getRandomNumber(8);
//		String vnp_IpAddr = Config.getIpAddress(req);
		String vnp_TmnCode = Config.vnp_TmnCode;//Mã fig cứng 

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
		vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);//Mã fig cứng 

		Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String vnp_CreateDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

		cld.add(Calendar.MINUTE, 15);
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

	@GET
	@Path(value = "/successfully")
	@Produces(MediaType.APPLICATION_JSON)
	public String findAllByProductId(@Context HttpServletRequest request) {

		String amount = request.getParameter("vnp_Amount");
		String bankCode = request.getParameter("vnp_BankCode");
		String curentCode = request.getParameter("vnp_CurrCode");
		String vndCardType = request.getParameter("vnp_CardType");
		String orderInfo = request.getParameter("vnp_OrderInfo");
		String codeOrder = request.getParameter("vnp_TmnCode");
		String date = request.getParameter("vnp_PayDate");
		String responseCode = request.getParameter("vnp_ResponseCode");


		List<String> stringReponse = new ArrayList<>();
//		stringReponse.add(tmCode);
		stringReponse.add(responseCode);
		stringReponse.add(amount);
		stringReponse.add(bankCode);
		stringReponse.add(curentCode);
		stringReponse.add(vndCardType);
		stringReponse.add(orderInfo);
		stringReponse.add(responseCode);
		stringReponse.add(date);

		return stringReponse.toString();
	}

	// http://localhost:8080/vnpay_jsp/vnpay_return.jsp?vnp_Amount=1000000&vnp_BankCode=NCB&vnp_BankTranNo=VNP14023780&vnp_CardType=ATM&vnp_OrderInfo=Thanh+toan+don+hang%3A72651602&vnp_PayDate=20230528084906&vnp_ResponseCode=00&vnp_TmnCode=AU9BUAID&vnp_TransactionNo=14023780&vnp_TransactionStatus=00&vnp_TxnRef=72651602&vnp_SecureHash=f7cb9d435bba8bf1a0722ec013b101e64fe7cdb24d6f1ae291953f00e7e219e1ceff76e46d9efa67e27226d1009884443526313c13c4aabaab204db840276e6c
}
