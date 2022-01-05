package com.dayrayaneh.automation.model.pishkhan.tickets.detilas;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("Status")
	private String status;

	@SerializedName("dateShamsi")
	private String dateShamsi;

	@SerializedName("UserName")
	private String userName;

	@SerializedName("Description")
	private String description;

	public String getStatus(){
		return status;
	}

	public String getDateShamsi(){
		return dateShamsi;
	}

	public String getUserName(){
		return userName;
	}

	public String getDescription(){
		return description;
	}
}