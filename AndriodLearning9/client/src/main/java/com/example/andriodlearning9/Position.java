package com.example.andriodlearning9;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yaochao on 2019/02/09
 */
public class Position implements Parcelable {

	private String title;

	private Integer salary;

	public Position() {
	}

	public Position(String title, Integer salary) {
		this.title = title;
		this.salary = salary;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(title);
		dest.writeInt(salary);
	}

	public final static Creator<Position> CREATOR = new Creator<Position>() {
		@Override
		public Position createFromParcel(Parcel source) {
			return new Position(source.readString(),source.readInt());
		}

		@Override
		public Position[] newArray(int size) {
			return new Position[size];
		}
	};
}
