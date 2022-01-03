package com.dayrayaneh.automation.model.pishkhan.tickets;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("Status")
	private String status;

	@SerializedName("Description")
	private String description;

	@SerializedName("TypeValue")
	private int typeValue;

	@SerializedName("Title")
	private String title;

	@SerializedName("CurrentUserCode")
	private int currentUserCode;

	@SerializedName("ImportanceValue")
	private int importanceValue;

	@SerializedName("statusNum")
	private int statusNum;

	@SerializedName("Importance")
	private String importance;

	@SerializedName("FirstUserCode")
	private int firstUserCode;

	@SerializedName("Type")
	private String type;

	@SerializedName("Id")
	private int id;

	@SerializedName("FirstUser")
	private String firstUser;

	@SerializedName("Shamsi")
	private String shamsi;

	@SerializedName("LastUser")
	private String lastUser;

	public String getStatus(){
		return status;
	}

	public String getDescription(){
		return description;
	}

	public int getTypeValue(){
		return typeValue;
	}

	public String getTitle(){
		return title;
	}

	public int getCurrentUserCode(){
		return currentUserCode;
	}

	public int getImportanceValue(){
		return importanceValue;
	}

	public int getStatusNum(){
		return statusNum;
	}

	public String getImportance(){
		return importance;
	}

	public int getFirstUserCode(){
		return firstUserCode;
	}

	public String getType(){
		return type;
	}

	public int getId(){
		return id;
	}

	public String getFirstUser(){
		return firstUser;
	}

	public String getShamsi(){
		return shamsi;
	}

	public String getLastUser(){
		return lastUser;
	}
}