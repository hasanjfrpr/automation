package com.dayrayaneh.automation.model.pishkhan.darsadThakhfifAzHarSefaresh;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("Fld_KindOperation_NameFarsi")
	private String fldKindOperationNameFarsi;

	@SerializedName("Fld_KindOperation_ID")
	private int fldKindOperationID;

	@SerializedName("PercentDiscount")
	private double percentDiscount;

	@SerializedName("TotalSell")
	private double totalSell;

	@SerializedName("TotalDiscount")
	private double totalDiscount;

	public String getFldKindOperationNameFarsi(){
		return fldKindOperationNameFarsi;
	}

	public int getFldKindOperationID(){
		return fldKindOperationID;
	}

	public double getPercentDiscount(){
		return percentDiscount;
	}

	public double getTotalSell(){
		return totalSell;
	}

	public double getTotalDiscount(){
		return totalDiscount;
	}
}