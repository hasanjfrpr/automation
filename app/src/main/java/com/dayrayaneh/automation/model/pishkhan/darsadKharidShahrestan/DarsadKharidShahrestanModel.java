package com.dayrayaneh.automation.model.pishkhan.darsadKharidShahrestan;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DarsadKharidShahrestanModel{

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