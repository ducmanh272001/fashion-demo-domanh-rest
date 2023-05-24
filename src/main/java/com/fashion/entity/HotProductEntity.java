package com.fashion.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hot_product")
public class HotProductEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "idsp")
	private Long idsp;
	@Column(name = "sell_number")
	private Long sellNumber;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdsp() {
		return idsp;
	}
	public void setIdsp(Long idsp) {
		this.idsp = idsp;
	}
	public Long getSellNumber() {
		return sellNumber;
	}
	public void setSellNumber(Long sellNumber) {
		this.sellNumber = sellNumber;
	}
	@Override
	public String toString() {
		return "HotProductEntity [id=" + id + ", idsp=" + idsp + ", sellNumber=" + sellNumber + "]";
	}
	
	
	

}
