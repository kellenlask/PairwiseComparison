package com.bk.fm.pairwisecomparison;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ResultsList extends ActionBarActivity {
//-------------------------------------------------------------
//
//		Fields
//
//-------------------------------------------------------------
	private ArrayList<String> items;
	private ListView itemList;

//-------------------------------------------------------------
//
//		onCreate
//
//-------------------------------------------------------------
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results_list);

		initializeComponents(savedInstanceState);

	}

//-------------------------------------------------------------
//
//		Logistical Methods
//
//-------------------------------------------------------------


//-------------------------------------------------------------
//
//		GUI Methods
//
//-------------------------------------------------------------

	public void initializeComponents(Bundle savedInstanceState) {
		itemList = (ListView) findViewById(R.id.itemList);

		items = (ArrayList<String>) getIntent().getSerializableExtra("ITEMS");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, items);
		itemList.setAdapter(adapter);
	}

} //End Class
