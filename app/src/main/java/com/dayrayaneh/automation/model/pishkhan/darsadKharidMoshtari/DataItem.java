package com.dayrayaneh.automation.model.pishkhan.darsadKharidMoshtari;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("CodeHesab")
	private String codeHesab;

	@SerializedName("PercentPercentOfAll")
	private double percentPercentOfAll;

	@SerializedName("MoshtarianId")
	private int moshtarianId;

	@SerializedName("PercentTotalCountSell")
	private int percentTotalCountSell;

	@SerializedName("MoshtariName")
	private String moshtariName;

	@SerializedName("PercentTotalPriceSell")
	private int percentTotalPriceSell;

	public String getCodeHesab(){
		return codeHesab;
	}

	public double getPercentPercentOfAll(){
		return percentPercentOfAll;
	}

	public int getMoshtarianId(){
		return moshtarianId;
	}

	public int getPercentTotalCountSell(){
		return percentTotalCountSell;
	}

	public String getMoshtariName(){
		return moshtariName;
	}

	public int getPercentTotalPriceSell(){
		return percentTotalPriceSell;
	}
}