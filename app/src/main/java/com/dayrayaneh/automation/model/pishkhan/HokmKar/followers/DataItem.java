package com.dayrayaneh.automation.model.pishkhan.HokmKar.followers;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("Fld_HokmAnjamkar_SharhL1")
	private String desc;

	@SerializedName("Personel")
	private String personel;

	@SerializedName("Fld_HokmFollow_SharhErjaL1")
	private String doDesc;

	@SerializedName("Fld_KindOperation_NameFarsi")
	private String kindName;

	public String getDesc(){
		return desc;
	}

	public String getPersonel(){
		return personel;
	}

	public String getDoDesc(){
		return doDesc;
	}

	public String getKindName(){
		return kindName;
	}
}