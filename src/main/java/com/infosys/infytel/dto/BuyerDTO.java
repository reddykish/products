package com.infosys.infytel.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class BuyerDTO {

	private String buyerId;
	@NotNull(message = "{customer.name.absent}")
	@Pattern(regexp = "[A-Za-z]+( [A-Za-z]+)*", message = "{customer.name.invalid}")
	private String name;
	@Email(message = "{customer.emailid.invalid}")
	@NotNull(message = "{customer.emailid.absent}")
	private String email;
	@Pattern(regexp="^[6-9][0-9]{9}$",message="customer.phoneNo.invalid")
	private long phoneNo;
	@NotNull(message="{Password must not be blank.}")
	@Pattern(regexp="^(?=.*[0-9]"+ "(?=.*[a-z])(?=.*[A-Z)"+ "(?=.*[@#$%^&+=])"+"(?=\\S+$).{7,20}$",message="Customer.password.invalid")
	private String password;
	private boolean isPriviliged;
	private Integer rewardPoints;
	private boolean isActive;
	
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isPriviliged() {
		return isPriviliged;
	}
	public void setPriviliged(boolean isPriviliged) {
		this.isPriviliged = isPriviliged;
	}
	public Integer getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(Integer rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public BuyerDTO() {
		super();
	}
	
	
}
