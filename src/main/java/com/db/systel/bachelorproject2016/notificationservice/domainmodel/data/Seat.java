package com.db.systel.bachelorproject2016.notificationservice.domainmodel.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Seat {

	private int seatClass;
	private String seatArea;
	private String seatLocation;
	private String seatCompartmentType;
	private boolean upperLevel;

	public Seat(@JsonProperty("seatClass") int seatClass, @JsonProperty("seatArea") String seatArea,
			@JsonProperty("seatLocation") String seatLocation,
			@JsonProperty("seatCompartmentType") String seatCompartmentType,
			@JsonProperty("upperLevel") boolean upperLevel) {
		this.setSeatClass(seatClass);
		this.setSeatArea(seatArea);
		this.setSeatLocation(seatLocation);
		this.setCompartmentType(seatCompartmentType);
		this.setUpperLevel(upperLevel);
	}

	public int getSeatClass() {
		return seatClass;
	}

	@JsonProperty("seatClass")
	public void setSeatClass(int seatClass) {
		this.seatClass = seatClass;
	}

	public String getSeatArea() {
		return seatArea;
	}

	@JsonProperty("seatArea")
	public void setSeatArea(String seatArea) {
		this.seatArea = seatArea;
	}

	public String getSeatLocation() {
		return seatLocation;
	}

	@JsonProperty("seatLocation")
	public void setSeatLocation(String seatLocation) {
		this.seatLocation = seatLocation;
	}

	public String getSeatSection() {
		return seatCompartmentType;
	}

	@JsonProperty("seatClass")
	public void setCompartmentType(String seatCompartmentType) {
		this.seatCompartmentType = seatCompartmentType;
	}

	public boolean isUpperLevel() {
		return upperLevel;
	}

	@JsonProperty("upperLevel")
	public void setUpperLevel(boolean upperLevel) {
		this.upperLevel = upperLevel;
	}

}
