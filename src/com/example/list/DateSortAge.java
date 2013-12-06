package com.example.list;

import java.util.Comparator;

public class DateSortAge implements Comparator<Student>{
	
	@Override
	public int compare(Student one, Student two) {
		// TODO Auto-generated method stub
		return one.dob - two.dob;
	}
}
	
