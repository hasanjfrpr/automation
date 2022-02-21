package com.dayrayaneh.automation.model.pishkhan.Gozareshkar.details;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("Fld_Report_SharhKarNameL1")
	private String fldReportSharhKarNameL1;

	@SerializedName("Fld_KindWork_NameL1")
	private String fldKindWorkNameL1;

	@SerializedName("Fld_Report_TimeStart")
	private String fldReportTimeStart;

	@SerializedName("Fld_Report_TimeEnd")
	private String fldReportTimeEnd;

	@SerializedName("Fld_Report_SharhKarDetailsNameL1")
	private String fldReportSharhKarDetailsNameL1;

	@SerializedName("FullName")
	private String fullName;

	@SerializedName("TotalTime")
	private int totalTime;

	public String getFldReportSharhKarNameL1(){
		return fldReportSharhKarNameL1;
	}

	public String getFldKindWorkNameL1(){
		return fldKindWorkNameL1;
	}

	public String getFldReportTimeStart(){
		return fldReportTimeStart;
	}

	public String getFldReportTimeEnd(){
		return fldReportTimeEnd;
	}

	public String getFldReportSharhKarDetailsNameL1(){
		return fldReportSharhKarDetailsNameL1;
	}

	public String getFullName(){
		return fullName;
	}

	public int getTotalTime(){
		return totalTime;
	}
}