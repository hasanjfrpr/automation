package com.dayrayaneh.automation.model.pishkhan.Gozareshkar.personalName;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("Id")
	private int id;

	@SerializedName("Name")
	private String name;

	public int getId(){
		return id;
	}

	public String getName(){
		return name;
	}
}