package com.fashion.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NEWS")
public class NewsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "DESCRIPE")
	private String descripe;
	@Column(name = "CONTENT")
	private String content;
	@Column(name = "TYPE_TIN")
	private String type_tin;
	@Column(name = "DAY_TIN")
	private Date day_tin;
	@Column(name = "IMG")
	private String img;
	@Column(name = "STATUS")
	private int status;

	public NewsEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewsEntity(int id, String title, String descripe, String content, String type_tin, Date day_tin, String img,
			int status) {
		super();
		this.id = id;
		this.title = title;
		this.descripe = descripe;
		this.content = content;
		this.type_tin = type_tin;
		this.day_tin = day_tin;
		this.img = img;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescripe() {
		return descripe;
	}

	public void setDescripe(String descripe) {
		this.descripe = descripe;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType_tin() {
		return type_tin;
	}

	public void setType_tin(String type_tin) {
		this.type_tin = type_tin;
	}

	public Date getDay_tin() {
		return day_tin;
	}

	public void setDay_tin(Date day_tin) {
		this.day_tin = day_tin;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TinTuc [id=" + id + ", title=" + title + ", descripe=" + descripe + ", content=" + content
				+ ", type_tin=" + type_tin + ", day_tin=" + day_tin + ", day_update=" + ", img=" + img + ", status="
				+ status + "]";
	}

}
