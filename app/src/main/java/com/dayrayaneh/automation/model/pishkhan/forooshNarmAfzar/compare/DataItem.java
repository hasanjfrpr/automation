package com.dayrayaneh.automation.model.pishkhan.forooshNarmAfzar.compare;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("tbl1AvgAmount")
	private double tbl1AvgAmount;

	@SerializedName("tbl2TotalCountSellSoft")
	private int tbl2TotalCountSellSoft;

	@SerializedName("tbl2Fld_KindOperation_ID")
	private int tbl2FldKindOperationID;

	@SerializedName("tbl1TotalPriceSellsoft")
	private double tbl1TotalPriceSellsoft;

	@SerializedName("tbl1TotalCountSellSoft")
	private double tbl1TotalCountSellSoft;

	@SerializedName("tbl2TotalPriceSellsoft")
	private double tbl2TotalPriceSellsoft;

	@SerializedName("tbl1Fld_KindOperation_NameFarsi")
	private String tbl1FldKindOperationNameFarsi;

	@SerializedName("tbl2AvgAmount")
	private double tbl2AvgAmount;

	@SerializedName("tbl2Fld_KindOperation_NameFarsi")
	private String tbl2FldKindOperationNameFarsi;

	@SerializedName("tbl1Fld_KindOperation_ID")
	private int tbl1FldKindOperationID;

	public double getTbl1AvgAmount(){
		return tbl1AvgAmount;
	}

	public int getTbl2TotalCountSellSoft(){
		return tbl2TotalCountSellSoft;
	}

	public int getTbl2FldKindOperationID(){
		return tbl2FldKindOperationID;
	}

	public double getTbl1TotalPriceSellsoft(){
		return tbl1TotalPriceSellsoft;
	}

	public double getTbl1TotalCountSellSoft(){
		return tbl1TotalCountSellSoft;
	}

	public double getTbl2TotalPriceSellsoft(){
		return tbl2TotalPriceSellsoft;
	}

	public String getTbl1FldKindOperationNameFarsi(){
		return tbl1FldKindOperationNameFarsi;
	}

	public double getTbl2AvgAmount(){
		return tbl2AvgAmount;
	}

	public String getTbl2FldKindOperationNameFarsi(){
		return tbl2FldKindOperationNameFarsi;
	}

	public int getTbl1FldKindOperationID(){
		return tbl1FldKindOperationID;
	}
}