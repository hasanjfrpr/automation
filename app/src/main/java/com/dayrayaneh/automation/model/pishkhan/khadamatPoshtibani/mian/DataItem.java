package com.dayrayaneh.automation.model.pishkhan.khadamatPoshtibani.mian;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("dakheliNumber")
	private String dakheliNumber;

	@SerializedName("totalCallTime")
	private String totalCallTime;

	@SerializedName("callCount")
	private String callCount;

	@SerializedName("sdCount")
	private int sdCount;

	@SerializedName("PoshtibanCode")
	private int poshtibanCode;

	@SerializedName("userId")
	private int userId;

	@SerializedName("point")
	private double point;

	@SerializedName("number")
	private String number;

	@SerializedName("Fld_Company")
	private int fldCompany;

	@SerializedName("avgTime")
	private String avgTime;

	@SerializedName("PoshtibanName")
	private String poshtibanName;

	@SerializedName("khadamatCount")
	private int khadamatCount;



	@SerializedName("name")
	private String name;

	public String getDakheliNumber(){
		return dakheliNumber;
	}

	public String getTotalCallTime(){
		return totalCallTime;
	}

	public String getCallCount(){
		return callCount;
	}

	public int getSdCount(){
		return sdCount;
	}

	public Object getPoshtibanCode(){
		return poshtibanCode;
	}

	public int getUserId(){
		return userId;
	}

	public double getPoint(){
		return point;
	}

	public String getNumber(){
		return number;
	}

	public int getFldCompany(){
		return fldCompany;
	}

	public String getAvgTime(){
		return avgTime;
	}

	public String getPoshtibanName(){
		return poshtibanName;
	}



	public int getKhadamatCount(){
		return khadamatCount;
	}

	public String getName(){
		return name;
	}
}