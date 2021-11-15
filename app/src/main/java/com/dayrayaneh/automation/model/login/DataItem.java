package com.dayrayaneh.automation.model.login;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataItem implements Parcelable {

	@SerializedName("UserName")
	private String userName;

	@SerializedName("AcceccMenuIdArray")
	private List<Integer> acceccMenuIdArray;

	@SerializedName("Token")
	private String token;

	@SerializedName("AcceccGroupIdArray")
	private List<Integer> acceccGroupIdArray;

	@SerializedName("Id")
	private int id;

	@SerializedName("Code")
	private int code;

	@SerializedName("Name")
	private String name;

	protected DataItem(Parcel in) {
		userName = in.readString();
		token = in.readString();
		id = in.readInt();
		code = in.readInt();
		name = in.readString();
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

	public String getUserName(){
		return userName;
	}

	public List<Integer> getAcceccMenuIdArray(){
		return acceccMenuIdArray;
	}

	public String getToken(){
		return token;
	}

	public List<Integer> getAcceccGroupIdArray(){
		return acceccGroupIdArray;
	}

	public int getId(){
		return id;
	}

	public int getCode(){
		return code;
	}

	public String getName(){
		return name;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(userName);
		dest.writeString(token);
		dest.writeInt(id);
		dest.writeInt(code);
		dest.writeString(name);
	}
}