package com.example.andriodlearning9;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yaochao on 2019/02/09
 */
public class Staff implements Parcelable {

	private String name;

	private String gender;

	public Staff() {
	}

	public Staff(String name, String gender) {
		this.name = name;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return gender;
	}

	public void setAge(String gender) {
		this.gender = gender;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(gender);
	}

	public final static Parcelable.Creator<Staff> CREATOR = new Parcelable.Creator<Staff>() {
		@Override
		public Staff createFromParcel(Parcel source) {
			return new Staff(source.readString(), source.readString());
		}

		@Override
		public Staff[] newArray(int size) {
			return new Staff[size];
		}
	};
}
