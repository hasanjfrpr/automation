package com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.count;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DataItem implements Parcelable {

	@SerializedName("ProformaCount")
	private int proformaCount;

	@SerializedName("Company")
	private int company;

	@SerializedName("Personel")
	private String personel;

	@SerializedName("PersonCode")
	private int personCode;

	protected DataItem(Parcel in) {
		proformaCount = in.readInt();
		company = in.readInt();
		personel = in.readString();
		personCode = in.readInt();
	}

	public static final Creator<DataItem> CREATOR = new Creator<DataItem>() {
		@Override
		public DataItem createFromParcel(Parcel in) {
			return new DataItem(in);
		}

		@Override
		public DataItem[] newArray(int size) {
			return new DataItem[size];
		}
	};

	public int getProformaCount(){
		return proformaCount;
	}

	public int getCompany(){
		return company;
	}

	public String getPersonel(){
		return personel;
	}

	public int getPersonCode(){
		return personCode;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(proformaCount);
		dest.writeInt(company);
		dest.writeString(personel);
		dest.writeInt(personCode);
	}
}