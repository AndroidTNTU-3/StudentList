package com.example.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public static final String EXTRA_FIRST_NAME = "first_name";
	public static final String EXTRA_LAST_NAME = "last_name";
	public static final String EXTRA_DOB = "dob";
	
	
	private AbsListView list;
	private BaseAdapter adapter;
	
	private Spinner spinner;
    private int flag_sort = 0;
	
	private ArrayList<Student> students;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		list = (AbsListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setSaveEnabled(false);

        //check if orientation has been changed
        if (savedInstanceState != null) {
            //students = (ArrayList<Student>) getLastNonConfigurationInstance();
            students = savedInstanceState.getParcelableArrayList("arrayOfStudents");
        }
		else students = Generator.generate();
		
		adapter = new StudentAdapter(students, getApplicationContext());

        /*if (savedInstanceState != null) {
            flag_sort = savedInstanceState.getInt("sort");
            switch (flag_sort){
                case 0:
                    Collections.sort(students, new DateSortName());
                    break;
                case 1:
                    Collections.sort(students, new DateSortAge());
                    break;
            }
        }*/
        if(list instanceof ListView){
            ListView lv = (ListView) list;
            lv.setAdapter(adapter);
        }
        else if(list instanceof GridView){
            GridView gv = (GridView) list;
            gv.setAdapter(adapter);
        }

        else list.setAdapter(adapter);
        if (flag_sort == 1)  Collections.sort(students, new DateSortName());
        if (flag_sort == 2) Collections.sort(students, new DateSortAge());
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				Intent intent = new Intent(MainActivity.this, DetailActivity.class);
				
				Student student = (Student) adapter.getItem(position);
				
				intent.putExtra(EXTRA_FIRST_NAME, student.firstName);
				intent.putExtra(EXTRA_LAST_NAME, student.lastName);
				intent.putExtra(EXTRA_DOB, student.dob);
				
				startActivity(intent);
				
			}
		});
		
		
		
		
		String [] titles = getResources().getStringArray(R.array.titles);
		
		spinner = (Spinner)findViewById(R.id.sort);
		
		final SpinnerAdapter spinnerAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item_spinner, R.id.text_item_spinner, titles);
		

		spinner.setAdapter(spinnerAdapter);
		
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(), "Sort by " + ((position ==0)? "name":"age"), Toast.LENGTH_LONG).show();
				
				switch(position){
				
				case 0: 
					Collections.sort(students, new DateSortName());
                    flag_sort = position;
				break;
				case 1:
					Collections.sort(students, new DateSortAge());
                    flag_sort = position;
				break;
				default:
					break;	
					
				}
				

				adapter.notifyDataSetChanged();
				
				adapter.notifyDataSetInvalidated();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
	}

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putInt("sort", flag_sort);
        outState.putParcelableArrayList("arrayOfStudents", students);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
       students = savedInstanceState.getParcelableArrayList("arrayOfStudents");
        //flag_sort = savedInstanceState.getInt("sort");
    }
   /*public Object onRetainNonConfigurationInstance() {
       return students;
   }*/

}
