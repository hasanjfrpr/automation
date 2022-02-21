package com.dayrayaneh.automation.model.pishkhan.vaziatSefareshat;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class VaziatSefareshatModel{

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