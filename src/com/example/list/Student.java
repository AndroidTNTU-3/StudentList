package com.example.list;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable{
	
	public final String firstName;
	public final String lastName;
	public final int dob;
	
	private String photo;

	public Student(String firstName, String lastName, int dob){
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName + "         " + dob;
	}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeInt(dob);
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        // распаковываем объект из Parcel
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };


    private Student(Parcel parcel){
        firstName = parcel.readString();
        lastName = parcel.readString();
        dob = parcel.readInt();
    }
}
