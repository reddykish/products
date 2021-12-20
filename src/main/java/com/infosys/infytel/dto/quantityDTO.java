package com.infosys.infytel.dto;

public class quantityDTO {
	String quantity;
	String prodId;
	String sellerId;
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	@Override
	public String toString() {
		return "quantityDTO [quantity=" + quantity + ", prodId=" + prodId + ", sellerId=" + sellerId + "]";
	}
	

}
