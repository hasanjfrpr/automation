package com.dayrayaneh.automation.model.pishkhan.darsadKharidShahrestan.details;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("total")
	private double total;

	@SerializedName("orderNumber")
	private double orderNumber;

	@SerializedName("Fullname")
	private String fullname;

	@SerializedName("price")
	private double price;

	@SerializedName("Kind")
	private String kind;

	@SerializedName("count")
	private double count;

	@SerializedName("model")
	private String model;

	@SerializedName("category")
	private String category;

	public double getTotal(){
		return total;
	}

	public double getOrderNumber(){
		return orderNumber;
	}

	public String getFullname(){
		return fullname;
	}

	public double getPrice(){
		return price;
	}

	public String getKind(){
		return kind;
	}

	public double getCount(){
		return count;
	}

	public String getModel(){
		return model;
	}

	public String getCategory(){
		return category;
	}
}