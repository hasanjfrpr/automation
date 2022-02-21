package com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("Fld_ProductModel_Model")
	private String fldProductModelModel;

	@SerializedName("AvgAmount")
	private double avgAmount;

	@SerializedName("Fld_ProductModel_Id")
	private double fldProductModelId;

	@SerializedName("TotalCountSellHard")
	private double totalCountSellHard;

	@SerializedName("TotalPriceSellHard")
	private double totalPriceSellHard;

	@SerializedName("Fld_ModelCategory_NameL1")
	private String fldModelCategoryNameL1;

	public String getFldProductModelModel(){
		return fldProductModelModel;
	}

	public double getAvgAmount(){
		return avgAmount;
	}

	public double getFldProductModelId(){
		return fldProductModelId;
	}

	public double getTotalCountSellHard(){
		return totalCountSellHard;
	}

	public double getTotalPriceSellHard(){
		return totalPriceSellHard;
	}

	public String getFldModelCategoryNameL1(){
		return fldModelCategoryNameL1;
	}
}