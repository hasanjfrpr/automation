package com.dayrayaneh.automation.model.pishkhan.tamdidQarardad;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("Fld_KhadamatGharardadKind_ID")
	private int fldKhadamatGharardadKindID;

	@SerializedName("Fld_KhadamatGharardadKind_NameL1")
	private String fldKhadamatGharardadKindNameL1;

	@SerializedName("Fld_KindOperation_ID")
	private int fldKindOperationID;

	@SerializedName("typeNameGharardad")
	private String typeNameGharardad;

	@SerializedName("CountGharardad")
	private int countGharardad;

	@SerializedName("mablagh_Gharardad")
	private double mablaghGharardad;

	public int getFldKhadamatGharardadKindID(){
		return fldKhadamatGharardadKindID;
	}

	public String getFldKhadamatGharardadKindNameL1(){
		return fldKhadamatGharardadKindNameL1;
	}

	public int getFldKindOperationID(){
		return fldKindOperationID;
	}

	public String getTypeNameGharardad(){
		return typeNameGharardad;
	}

	public int getCountGharardad(){
		return countGharardad;
	}

	public double getMablaghGharardad(){
		return mablaghGharardad;
	}
}