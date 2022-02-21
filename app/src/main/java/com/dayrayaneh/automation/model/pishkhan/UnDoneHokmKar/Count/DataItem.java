package com.dayrayaneh.automation.model.pishkhan.UnDoneHokmKar.Count;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("count")
	private int count;

	@SerializedName("Personel")
	private String personel;

	@SerializedName("PCode")
	private int pCode;

	public int getCount(){
		return count;
	}

	public String getPersonel(){
		return personel;
	}

	public int getPCode(){
		return pCode;
	}
}