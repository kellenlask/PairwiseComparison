package com.bk.fm.pairwisecomparison;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

		// Set ActionBar Color
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0d5c92")));

		initializeComponents();

	}

	@Override
	public void onBackPressed() {
		Intent i = new Intent(getBaseContext(), ItemsList.class);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(i);
	}

//-------------------------------------------------------------
//
//		Logistical Methods
//
//-------------------------------------------------------------

	public void initializeComponents() {
		itemList = (ListView) findViewById(R.id.itemList);

		items = (ArrayList<String>) getIntent().getSerializableExtra("ITEMS");

		for(int i = 0; i < items.size(); i++) {
			items.set(i, (i + 1) + ".\t" + items.get(i));
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
		itemList.setAdapter(adapter);
	}

} //End Class
