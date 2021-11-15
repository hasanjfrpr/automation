package com.dayrayaneh.automation.model.login;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class LoginModel implements Parcelable {

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("status")
	private Status status;

	protected LoginModel(Parcel in) {
		data = in.createTypedArrayList(DataItem.CREATOR);
	}

	public static final Creator<LoginModel> CREATOR = new Creator<LoginModel>() {
		@Override
		public LoginModel createFromParcel(Parcel in) {
			return new LoginModel(in);
		}

		@Override
		public LoginModel[] newArray(int size) {
			return new LoginModel[size];
		}
	};

	public List<DataItem> getData(){
		return data;
	}

	public Status getStatus(){
		return status;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeTypedList(data);
	}
}