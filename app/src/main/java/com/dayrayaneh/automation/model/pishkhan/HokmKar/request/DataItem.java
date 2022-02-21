package com.dayrayaneh.automation.model.pishkhan.HokmKar.request;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("Fld_HokmRequest_SharhL1")
	private String request;

	public String getRequest(){
		return request;
	}
}