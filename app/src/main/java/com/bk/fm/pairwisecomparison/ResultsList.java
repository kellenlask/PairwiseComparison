package com.bk.fm.pairwisecomparison;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;


public class ResultsList extends AppCompatActivity {
//-------------------------------------------------------------
//
//		Fields
//
//-------------------------------------------------------------
	private ArrayList<String> items;
	private NumberedListAdapter adapter;
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

		// Set ActionBar Color
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0d5c92")));

		initializeComponents();

	}

	@Override
	public void onBackPressed() {
		Intent i = new Intent(getBaseContext(), ItemsList.class);
		i.putExtra("ITEMS",  items);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(i);
	}

//-------------------------------------------------------------
//
//		Logistical Methods
//
//-------------------------------------------------------------

	// Tell the adapter that its data has changed
	public void updateList() {
		if(adapter == null) {
			adapter = new NumberedListAdapter(getBaseContext(), items);
			itemList.setAdapter(adapter);

		} else {
			adapter.reloadData();
		}
	}


	public void initializeComponents() {
		itemList = (ListView) findViewById(R.id.itemList);

		items = (ArrayList<String>) getIntent().getSerializableExtra("ITEMS");

		updateList();
	}

} //End Class
