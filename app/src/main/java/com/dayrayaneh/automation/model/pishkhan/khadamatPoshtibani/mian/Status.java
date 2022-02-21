package com.dayrayaneh.automation.model.pishkhan.khadamatPoshtibani.mian;

import com.google.gson.annotations.SerializedName;

public class Status{

	@SerializedName("Message")
	private String message;

	@SerializedName("IsError")
	private boolean isError;

	public String getMessage(){
		return message;
	}

	public boolean isIsError(){
		return isError;
	}
}