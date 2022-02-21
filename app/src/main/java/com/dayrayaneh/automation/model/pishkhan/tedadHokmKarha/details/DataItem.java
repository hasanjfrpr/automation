package com.dayrayaneh.automation.model.pishkhan.tedadHokmKarha.details;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("Fld_KindOperation_NameFarsi")
	private String fldKindOperationNameFarsi;

	@SerializedName("Fld_HokmFollow_SharhErjaL1")
	private String fldHokmFollowSharhErjaL1;

	@SerializedName("Fld_HokmFollow_Time")
	private String fldHokmFollowTime;

	@SerializedName("Serial")
	private String serial;

	@SerializedName("HokmkarCode")
	private int hokmkarCode;

	@SerializedName("Fld_HokmKar_Number")
	private double fldHokmKarNumber;

	@SerializedName("erjaDahande")
	private String erjaDahande;

	@SerializedName("Fld_HokmFollow_DateEnd")
	private String fldHokmFollowDateEnd;

	public String getFldKindOperationNameFarsi(){
		return fldKindOperationNameFarsi;
	}

	public String getFldHokmFollowSharhErjaL1(){
		return fldHokmFollowSharhErjaL1;
	}

	public String getFldHokmFollowTime(){
		return fldHokmFollowTime;
	}

	public int getHokmkarCode(){
		return hokmkarCode;
	}

	public double getFldHokmKarNumber(){
		return fldHokmKarNumber;
	}

	public String getErjaDahande(){
		return erjaDahande;
	}

	public String getSerial(){
		return serial;
	}

	public String getFldHokmFollowDateEnd(){
		return fldHokmFollowDateEnd;
	}
}