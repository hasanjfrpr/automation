package com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.count;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class BazaryabiMainModel implements Parcelable {

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("status")
	private Status status;

	protected BazaryabiMainModel(Parcel in) {
		data  = new ArrayList<DataItem>();
		in.readList(data , DataItem.class.getClassLoader());
	}

	public static final Creator<BazaryabiMainModel> CREATOR = new Creator<BazaryabiMainModel>() {
		@Override
		public BazaryabiMainModel createFromParcel(Parcel in) {
			return new BazaryabiMainModel(in);
		}

		@Override
		public BazaryabiMainModel[] newArray(int size) {
			return new BazaryabiMainModel[size];
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
		dest.writeList(data);
	}
}