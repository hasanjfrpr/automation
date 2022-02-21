package com.dayrayaneh.automation.model.pishkhan.sefareshMoshtariJadid;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("newCustomerMoshtarianID")
	private int newCustomerMoshtarianID;

	@SerializedName("newcustomerOrderNumber")
	private double newcustomerOrderNumber;

	@SerializedName("newCustomerKindKhadamatNameL1")
	private String newCustomerKindKhadamatNameL1;

	@SerializedName("newCustomerOrderPrice")
	private double newCustomerOrderPrice;

	@SerializedName("newCustomerMoshtarianCodeHesab")
	private String newCustomerMoshtarianCodeHesab;

	@SerializedName("newCustomerFullName")
	private String newCustomerFullName;

	public int getNewCustomerMoshtarianID(){
		return newCustomerMoshtarianID;
	}

	public double getNewcustomerOrderNumber(){
		return newcustomerOrderNumber;
	}

	public String getNewCustomerKindKhadamatNameL1(){
		return newCustomerKindKhadamatNameL1;
	}

	public double getNewCustomerOrderPrice(){
		return newCustomerOrderPrice;
	}

	public String getNewCustomerMoshtarianCodeHesab(){
		return newCustomerMoshtarianCodeHesab;
	}

	public String getNewCustomerFullName(){
		return newCustomerFullName;
	}
}