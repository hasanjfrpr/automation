package com.dayrayaneh.automation.model.pishkhan.tedadHokmKarha.count;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("fullName")
	private String fullName;

	@SerializedName("PersonCode")
	private int personCode;

	@SerializedName("Count")
	private int hokmKarCount;

	@SerializedName("erjaCount")
	private int erjaCount;


	public int getHokmKarCount(){ return hokmKarCount; }

	public int getErjaCount(){ return erjaCount; }

	public String getFullName(){
		return fullName;
	}

	public int getPersonCode(){
		return personCode;
	}
}