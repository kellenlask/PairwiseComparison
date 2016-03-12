package com.bk.fm.pairwisecomparison;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ItemsList extends ActionBarActivity {
//--------------------------------------------------------------------
//
//		Fields
//
//--------------------------------------------------------------------
	private ArrayList<String> items;
	private ListView itemList;
	private Button addButton;
	private TextView input;
	private Button next;

//--------------------------------------------------------------------
//
//		onCreate
//
//--------------------------------------------------------------------

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_items_list);

		// Set ActionBar Color
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0d5c92")));

		initializeComponents();
		addButtonHandlers();
	}


//--------------------------------------------------------------------
//
//		Logical Methods
//
//--------------------------------------------------------------------
	public void addButtonHandlers(){
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				updateList(input.getText().toString());
			}
		});

		next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (items.size() >= 2) {
					Intent i = new Intent(getBaseContext(), Comparison.class);
					i.putExtra("ITEMS",  items);
					startActivity(i);
				} else {
					Toast.makeText(getApplicationContext(), getString(R.string.warning1), Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	public void updateList(String s) {
		if(!items.contains(s)) {
			items.add(s);

			ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, items);
			itemList.setAdapter(adapter);

			input.setText("");
		}

	}

//--------------------------------------------------------------------
//
//		GUI Methods
//
//--------------------------------------------------------------------
	public void initializeComponents() {
		items = new ArrayList<>();
		itemList = (ListView) findViewById(R.id.itemList);
		addButton = (Button) findViewById(R.id.addButton);
		input = (TextView) findViewById(R.id.inputField);
		next = (Button) findViewById(R.id.nextButton);
	}


} //End Class
