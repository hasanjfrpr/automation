package com.dayrayaneh.automation.model.pishkhan.vaziatSefareshat;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("Fld_Moshtarian_ID")
	private int fldMoshtarianID;

	@SerializedName("Fld_Order_Price")
	private double fldOrderPrice;

	@SerializedName("StatusOrder")
	private String statusOrder;

	@SerializedName("FullName")
	private String fullName;

	@SerializedName("Fld_Order_Number")
	private double fldOrderNumber;

	@SerializedName("Fld_Moshtarian_CodeHesab")
	private String fldMoshtarianCodeHesab;

	@SerializedName("NahveAshnaye")
	private String nahveAshnaye;

	@SerializedName("Fld_KindKhadamat_NameL1")
	private String fldKindKhadamatNameL1;

	public int getFldMoshtarianID(){
		return fldMoshtarianID;
	}

	public double getFldOrderPrice(){
		return fldOrderPrice;
	}

	public String getStatusOrder(){
		return statusOrder;
	}

	public String getFullName(){
		return fullName;
	}

	public double getFldOrderNumber(){
		return fldOrderNumber;
	}

	public String getFldMoshtarianCodeHesab(){
		return fldMoshtarianCodeHesab;
	}

	public String getNahveAshnaye(){
		return nahveAshnaye;
	}

	public String getFldKindKhadamatNameL1(){
		return fldKindKhadamatNameL1;
	}
}