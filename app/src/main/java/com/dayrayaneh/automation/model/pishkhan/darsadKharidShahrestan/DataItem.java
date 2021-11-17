package com.dayrayaneh.automation.model.pishkhan.darsadKharidShahrestan;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("PercentPercentOfAll")
	private double percentPercentOfAll;

	@SerializedName("PercentTotalCountSell")
	private int percentTotalCountSell;

	@SerializedName("cityId")
	private int cityId;

	@SerializedName("City")
	private String city;

	@SerializedName("PercentTotalPriceSell")
	private double percentTotalPriceSell;

	public double getPercentPercentOfAll(){
		return percentPercentOfAll;
	}

	public int getPercentTotalCountSell(){
		return percentTotalCountSell;
	}

	public int getCityId(){
		return cityId;
	}

	public String getCity(){
		return city;
	}

	public double getPercentTotalPriceSell(){
		return percentTotalPriceSell;
	}
}