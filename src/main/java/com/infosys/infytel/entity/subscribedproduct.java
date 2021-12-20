package com.infosys.infytel.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(prokeys.class)
public class subscribedproduct {
	@Id
	private String buyerId;
	@Id
	private String prodId;
	private String quantity;
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "subscribedproduct [buyerId=" + buyerId + ", prodId=" + prodId + ", quantity=" + quantity + "]";
	}
	

}
