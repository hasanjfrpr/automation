package com.dayrayaneh.automation.model.pishkhan.forooshNarmAfzar;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("AvgAmount")
	private int avgAmount;

	@SerializedName("TotalCountSellSoft")
	private int totalCountSellSoft;

	@SerializedName("Fld_KindOperation_NameFarsi")
	private String fldKindOperationNameFarsi;

	@SerializedName("Fld_KindOperation_ID")
	private int fldKindOperationID;

	@SerializedName("TotalPriceSellsoft")
	private int totalPriceSellsoft;

	public int getAvgAmount(){
		return avgAmount;
	}

	public int getTotalCountSellSoft(){
		return totalCountSellSoft;
	}

	public String getFldKindOperationNameFarsi(){
		return fldKindOperationNameFarsi;
	}

	public int getFldKindOperationID(){
		return fldKindOperationID;
	}

	public int getTotalPriceSellsoft(){
		return totalPriceSellsoft;
	}
}