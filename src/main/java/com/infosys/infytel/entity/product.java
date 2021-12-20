package com.infosys.infytel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class product {
	
	@Id
	private String prodId;
	@Column(nullable = false)
	private String prodName;
	private Integer price;
	private Integer stock;
	private String description;
	@Column(nullable = false)
	private String sellerId;
	private String category;
	private String subCateg;
	private Float prodRating;
	private String image;
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCateg() {
		return subCateg;
	}
	public void setSubCateg(String subCateg) {
		this.subCateg = subCateg;
	}
	public Float getProdRating() {
		return prodRating;
	}
	public void setProdRating(Float prodRating) {
		this.prodRating = prodRating;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "product [prodId=" + prodId + ", prodName=" + prodName + ", price=" + price + ", stock=" + stock
				+ ", description=" + description + ", sellerId=" + sellerId + ", category=" + category + ", subCateg="
				+ subCateg + ", prodRating=" + prodRating + ", image=" + image + "]";
	}
	
	
}
