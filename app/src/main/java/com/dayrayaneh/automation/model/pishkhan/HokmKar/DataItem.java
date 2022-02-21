package com.dayrayaneh.automation.model.pishkhan.HokmKar;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("Fld_HokmKar_Number")
	private double number;

	@SerializedName("Fld_Moshtarian_HoghoghiNameL1")
	private String customer;

	@SerializedName("Fld_Lock_Serial")
	private String serial;

	@SerializedName("Fld_KindKhadamat_NameL1")
	private String kind;

	@SerializedName("HokmKarCode")
	private int id;

	@SerializedName("Fld_User_NameL1")
	private String name;

	public double getNumber(){
		return number;
	}

	public String getCustomer(){
		return customer;
	}

	public String getSerial(){
		return serial;
	}

	public String getKind(){
		return kind;
	}

	public int getId(){
		return id;
	}

	public String getName(){
		return name;
	}
}