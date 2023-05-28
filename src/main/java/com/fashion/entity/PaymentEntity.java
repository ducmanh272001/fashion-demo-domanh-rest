package com.fashion.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Payment")
public class PaymentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "amount")
	private Long amount;// So tien
	@Column(name = "bank_code")
	private String bankCode;// Ngan hang
	@Column(name = "currency")
	private String currency;// don vi tien te
	@Column(name = "card_type")
	private String cardType;// Kiểu thanh toán atm
	@Column(name = "date_payment")
	private Date datePayment;
	@Column(name = "response")
	private String response;
	@Column(name = "idhd")
	private Integer idhd;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Date getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Integer getIdhd() {
		return idhd;
	}

	public void setIdhd(Integer idhd) {
		this.idhd = idhd;
	}

	public PaymentEntity(int id, Long amount, String bankCode, String currency, String cardType, Date datePayment,
			String response, Integer idhd) {
		super();
		this.id = id;
		this.amount = amount;
		this.bankCode = bankCode;
		this.currency = currency;
		this.cardType = cardType;
		this.datePayment = datePayment;
		this.response = response;
		this.idhd = idhd;
	}

	public PaymentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
