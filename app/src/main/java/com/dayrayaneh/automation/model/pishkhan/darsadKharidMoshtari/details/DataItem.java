package com.dayrayaneh.automation.model.pishkhan.darsadKharidMoshtari.details;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("DateTimeShamsi")
	private String dateTimeShamsi;

	@SerializedName("Fld_OrderProduct_Amount")
	private double fldOrderProductAmount;

	@SerializedName("Fld_KindOperation_NameFarsi")
	private String fldKindOperationNameFarsi;

	@SerializedName("Fld_Order_Number")
	private double fldOrderNumber;

	@SerializedName("PercentFld_Moshtarian_HoghoghiNameL1")
	private String percentFldMoshtarianHoghoghiNameL1;

	@SerializedName("percentFld_Moshtarian_CodeHesab")
	private String percentFldMoshtarianCodeHesab;

	@SerializedName("model")
	private String model;

	public String getDateTimeShamsi(){
		return dateTimeShamsi;
	}

	public double getFldOrderProductAmount(){
		return fldOrderProductAmount;
	}

	public String getFldKindOperationNameFarsi(){
		return fldKindOperationNameFarsi;
	}

	public double getFldOrderNumber(){
		return fldOrderNumber;
	}

	public String getPercentFldMoshtarianHoghoghiNameL1(){
		return percentFldMoshtarianHoghoghiNameL1;
	}

	public String getPercentFldMoshtarianCodeHesab(){
		return percentFldMoshtarianCodeHesab;
	}

	public String getModel(){
		return model;
	}
}