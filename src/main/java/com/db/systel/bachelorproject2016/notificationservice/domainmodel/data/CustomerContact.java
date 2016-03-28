package com.db.systel.bachelorproject2016.notificationservice.domainmodel.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerContact {

	private String eMailAddress;
	private Integer customerID;

	public CustomerContact(@JsonProperty("customerID") Integer customerID,
			@JsonProperty("eMailAddress") String eMailAddress) {
		setCustomerID(customerID);
		setEMailAddress(eMailAddress);
	}

	public String getEMailAddress() {
		return this.eMailAddress;
	}

	@JsonProperty("eMailAddress")
	public void setEMailAddress(String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}

	public int getCustomerID() {
		return this.customerID;
	}

	@JsonProperty("customerID")
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
}
