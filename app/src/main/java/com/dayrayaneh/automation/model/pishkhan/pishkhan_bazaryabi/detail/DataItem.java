package com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.detail;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("Moshtari")
	private String moshtari;

	@SerializedName("Description")
	private String description;

	@SerializedName("FullName")
	private String fullName;

	@SerializedName("khadamatType")
	private String khadamatType;

	@SerializedName("TelNumber")
	private String telNumber;

	@SerializedName("Vaziat")
	private String vaziat;

	@SerializedName("NueMahsol")
	private String nueMahsol;

	public String getMoshtari(){
		return moshtari;
	}

	public String getDescription(){
		return description;
	}

	public String getFullName(){
		return fullName;
	}

	public String getKhadamatType(){
		return khadamatType;
	}

	public String getTelNumber(){
		return telNumber;
	}

	public String getVaziat(){
		return vaziat;
	}

	public String getNueMahsol(){
		return nueMahsol;
	}
}