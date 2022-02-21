package com.dayrayaneh.automation.model.pishkhan.forooshNarmAfzar.compare;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ForooshNarmAfzarCompareModel{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("status")
	private Status status;

	public List<DataItem> getData(){
		return data;
	}

	public Status getStatus(){
		return status;
	}
}