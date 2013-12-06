package com.example.list;

import java.util.Comparator;

public class DateSortName implements Comparator<Student>{

	@Override
	public int compare(Student one, Student two) {
		// TODO Auto-generated method stub
		return one.firstName.compareTo(two.firstName);
	}

}
