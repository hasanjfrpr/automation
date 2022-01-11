package com.dayrayaneh.automation.model.pishkhan.UnDoneHokmKar;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("Moshtari")
	private String moshtari;

	@SerializedName("Serial")
	private String serial;

	@SerializedName("HokmNumber")
	private double hokmNumber;

	@SerializedName("KhadamatType")
	private String khadamatType;

	@SerializedName("SabtKonande")
	private String sabtKonande;

	@SerializedName("Personel")
	private String personel;

	@SerializedName("Id")
	private int id;

	@SerializedName("Requests")
	private Object requests;

	public String getMoshtari(){
		return moshtari;
	}

	public String getSerial(){
		return serial;
	}

	public double getHokmNumber(){
		return hokmNumber;
	}

	public String getKhadamatType(){
		return khadamatType;
	}

	public String getSabtKonande(){
		return sabtKonande;
	}

	public String getPersonel(){
		return personel;
	}

	public int getId(){
		return id;
	}

	public Object getRequests(){
		return requests;
	}
}