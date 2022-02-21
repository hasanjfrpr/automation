package com.dayrayaneh.automation.model.pishkhan.voicePoshtibani;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("DateTimeShamsi")
	private String dateTimeShamsi;

	@SerializedName("serial")
	private String serial;

	@SerializedName("phone_num")
	private String phoneNum;

	@SerializedName("uniqid")
	private String uniqid;

	@SerializedName("id")
	private int id;

	@SerializedName("Datetime")
	private String datetime;

	public String getDateTimeShamsi(){
		return dateTimeShamsi;
	}

	public String getSerial(){
		return serial;
	}

	public String getPhoneNum(){
		return phoneNum;
	}

	public String getUniqid(){
		return uniqid;
	}

	public int getId(){
		return id;
	}

	public String getDatetime(){
		return datetime;
	}
}