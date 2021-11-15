package com.dayrayaneh.automation.model.pishkhan.darsadSefareshat;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("Fld_KindOperation_NameFarsi")
	private String fldKindOperationNameFarsi;

	@SerializedName("Fld_KindOperation_ID")
	private int fldKindOperationID;

	@SerializedName("percentOf")
	private double percentOf;

	public String getFldKindOperationNameFarsi(){
		return fldKindOperationNameFarsi;
	}

	public int getFldKindOperationID(){
		return fldKindOperationID;
	}

	public double getPercentOf(){
		return percentOf;
	}
}