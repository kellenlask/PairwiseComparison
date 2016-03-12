package com.bk.fm.pairwisecomparison;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;


public class Comparison extends ActionBarActivity {
//--------------------------------------------------------------------
//
//		Fields
//
//--------------------------------------------------------------------
	private ArrayList<String> items;
	private int totalComparisons;
	private int placeOne;
	private int placeTwo;
	private ArrayList<String> priorities;

	private Button itemOne;
	private Button itemTwo;
	private ProgressBar progress;

//--------------------------------------------------------------------
//
//		onCreate
//
//--------------------------------------------------------------------
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comparison);

		// Set ActionBar Color
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0d5c92")));

		initializeComponents();

		addButtonHandlers();

	}

//--------------------------------------------------------------------
//
//		Logistical Methods
//
//--------------------------------------------------------------------

	public void addButtonHandlers() {
		View.OnClickListener listener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(v == itemOne) {
					int indexOne = priorities.indexOf(itemOne.getText().toString());
					int indexTwo = priorities.indexOf(itemTwo.getText().toString());

					if(indexOne > indexTwo) {
						priorities.remove(indexOne);
						priorities.add(indexTwo, itemOne.getText().toString());
					}
				} else {
					int indexOne = priorities.indexOf(itemOne.getText().toString());
					int indexTwo = priorities.indexOf(itemTwo.getText().toString());

					if(indexOne < indexTwo) {
						priorities.remove(indexTwo);
						priorities.add(indexOne, itemTwo.getText().toString());
					}
				}

				setButtons();

			}
		};

		itemOne.setOnClickListener(listener);
		itemTwo.setOnClickListener(listener);

	}


//--------------------------------------------------------------------
//
//		GUI Methods
//
//--------------------------------------------------------------------

	public void setButtons() {
		progress.incrementProgressBy(1);

		if(placeOne == items.size() - 1 && placeTwo == items.size()) {
			openNextActivity();

		} else {
			itemOne.setText(items.get(placeOne));
			itemTwo.setText(items.get(placeTwo));

			if(placeTwo == items.size() - 1) {
				placeOne++;
				placeTwo = placeOne + 1;

			} else {
				placeTwo++;
			}
		}
	}

	public void openNextActivity() {
		Intent i = new Intent(getBaseContext(), ResultsList.class);
		i.putExtra("ITEMS",  priorities);
		startActivity(i);
	}

	public void initializeComponents() {
		items = (ArrayList<String>) getIntent().getSerializableExtra("ITEMS");
		priorities = new ArrayList<>(items);
		placeOne = 0;
		placeTwo = 1;

		progress = (ProgressBar) findViewById(R.id.progressBar);
		itemOne = (Button) findViewById(R.id.itemOne);
		itemTwo = (Button) findViewById(R.id.itemTwo);

		int n = items.size();
		totalComparisons = n * (n - 1) / 2;

		progress.setMax(totalComparisons + 1);
		progress.setProgress(0);

		setButtons();

	}//End public void initializeComponents(Bundle)

} //End Class
