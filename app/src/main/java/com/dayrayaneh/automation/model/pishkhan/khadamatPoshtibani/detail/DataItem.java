package com.dayrayaneh.automation.model.pishkhan.khadamatPoshtibani.detail;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("Fld_KhadamatSupport_DateDShamsi")
	private String fldKhadamatSupportDateDShamsi;

	@SerializedName("Fld_KhadamatSupport_ID")
	private String fldKhadamatSupportID;

	@SerializedName("Rquest")
	private String rquest;

	@SerializedName("Fld_KhadamatSupport_DateDMiladi")
	private String fldKhadamatSupportDateDMiladi;

	@SerializedName("TimeSupport")
	private String timeSupport;

	@SerializedName("point")
	private String point;

	@SerializedName("Serial")
	private String serial;

	@SerializedName("Fld_Mokaleme_ID")
	private String fldMokalemeID;

	@SerializedName("Speak")
	private String speak;

	@SerializedName("TozihBeMoshtari")
	private String tozihBeMoshtari;

	@SerializedName("moshtariName")
	private String moshtariName;

	@SerializedName("Personel")
	private String personel;

	@SerializedName("kindName")
	private String kindName;

	public String getFldKhadamatSupportDateDShamsi(){
		return fldKhadamatSupportDateDShamsi;
	}

	public String getFldKhadamatSupportID(){
		return fldKhadamatSupportID;
	}

	public String getRquest(){
		return rquest;
	}

	public String getFldKhadamatSupportDateDMiladi(){
		return fldKhadamatSupportDateDMiladi;
	}

	public String getTimeSupport(){
		return timeSupport;
	}

	public String getPoint(){
		return point;
	}

	public String getSerial(){
		return serial;
	}

	public String getFldMokalemeID(){
		return fldMokalemeID;
	}

	public String getSpeak(){
		return speak;
	}

	public String getTozihBeMoshtari(){
		return tozihBeMoshtari;
	}

	public String getMoshtariName(){
		return moshtariName;
	}

	public String getPersonel(){
		return personel;
	}

	public String getKindName(){
		return kindName;
	}
}