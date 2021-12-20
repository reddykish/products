package com.infosys.infytel.dto;


public class subscribedproductDTO {
	private String buyerId;
	
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
