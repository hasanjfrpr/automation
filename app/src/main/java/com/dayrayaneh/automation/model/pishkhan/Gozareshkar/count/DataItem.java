package com.dayrayaneh.automation.model.pishkhan.Gozareshkar.count;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("UserCode")
	private int userCode;

	@SerializedName("TotalTimeWorkReport")
	private int totalTimeWorkReport;

	@SerializedName("CountReport")
	private int countReport;

	@SerializedName("FullNameWork")
	private String fullNameWork;

	public int getUserCode(){
		return userCode;
	}

	public int getTotalTimeWorkReport(){
		return totalTimeWorkReport;
	}

	public int getCountReport(){
		return countReport;
	}

	public String getFullNameWork(){
		return fullNameWork;
	}
}