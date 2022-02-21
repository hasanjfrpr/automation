package com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar.compare;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("tbl1TotalCountSellHard")
	private double tbl1TotalCountSellHard;

	@SerializedName("tbl1AvgAmount")
	private double tbl1AvgAmount;

	@SerializedName("tbl2Fld_ProductModel_Id")
	private double tbl2FldProductModelId;

	@SerializedName("tbl1TotalPriceSellHard")
	private double tbl1TotalPriceSellHard;

	@SerializedName("tbl2TotalCountSellHard")
	private double tbl2TotalCountSellHard;

	@SerializedName("tbl2TotalPriceSellHard")
	private double tbl2TotalPriceSellHard;

	@SerializedName("tbl2AvgAmount")
	private double tbl2AvgAmount;

	@SerializedName("tbl1Fld_ProductModel_Model")
	private String tbl1FldProductModelModel;

	@SerializedName("tbl1Fld_ProductModel_Id")
	private double tbl1FldProductModelId;

	@SerializedName("tbl2Fld_ProductModel_Model")
	private String tbl2FldProductModelModel;

	@SerializedName("tbl1Fld_ModelCategory_NameL1")
	private String tbl1FldModelCategoryNameL1;

	@SerializedName("tbl2Fld_ModelCategory_NameL1")
	private String tbl2FldModelCategoryNameL1;

	public double getTbl1TotalCountSellHard(){
		return tbl1TotalCountSellHard;
	}

	public double getTbl1AvgAmount(){
		return tbl1AvgAmount;
	}

	public double getTbl2FldProductModelId(){
		return tbl2FldProductModelId;
	}

	public double getTbl1TotalPriceSellHard(){
		return tbl1TotalPriceSellHard;
	}

	public double getTbl2TotalCountSellHard(){
		return tbl2TotalCountSellHard;
	}

	public double getTbl2TotalPriceSellHard(){
		return tbl2TotalPriceSellHard;
	}

	public double getTbl2AvgAmount(){
		return tbl2AvgAmount;
	}

	public String getTbl1FldProductModelModel(){
		return tbl1FldProductModelModel;
	}

	public double getTbl1FldProductModelId(){
		return tbl1FldProductModelId;
	}

	public String getTbl2FldProductModelModel(){
		return tbl2FldProductModelModel;
	}

	public String getTbl1FldModelCategoryNameL1(){
		return tbl1FldModelCategoryNameL1;
	}

	public String getTbl2FldModelCategoryNameL1(){
		return tbl2FldModelCategoryNameL1;
	}
}