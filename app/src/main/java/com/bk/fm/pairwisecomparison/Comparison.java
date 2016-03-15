package com.bk.fm.pairwisecomparison;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;


public class Comparison extends AppCompatActivity {
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

	Animation fade;

//--------------------------------------------------------------------
//
//		onCreate
//
//--------------------------------------------------------------------
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comparison);

		initializeComponents();
		addButtonHandlers();
	}

//--------------------------------------------------------------------
//
//		Logistical Methods
//
//--------------------------------------------------------------------

	// Adds action handlers to the two comparison buttons.
	public void addButtonHandlers() {
		// Define a common onClick listener for the two buttons
		View.OnClickListener listener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Get the current priority for each item
				int indexOne = priorities.indexOf(itemOne.getText().toString());
				int indexTwo = priorities.indexOf(itemTwo.getText().toString());

				// Determine which button was clicked, and if necessary, swap item priorities
				if(v == itemOne) {
					if(indexOne > indexTwo) {
						priorities.remove(indexOne);
						priorities.add(indexTwo, itemOne.getText().toString());
					}

				} else {
					if(indexOne < indexTwo) {
						priorities.remove(indexTwo);
						priorities.add(indexOne, itemTwo.getText().toString());
					}
				}

				// Change the button text for the next comparison
				setButtons();
			}
		};

		// Attach the common action handler to the buttons
		itemOne.setOnClickListener(listener);
		itemTwo.setOnClickListener(listener);

	}


//--------------------------------------------------------------------
//
//		GUI Methods
//
//--------------------------------------------------------------------

	// Updates the buttons to show the next comparison, or, if there are no more comparisons, moves
	// to the next activity.
	public void setButtons() {
		progress.incrementProgressBy(1);

		if(placeOne == items.size() - 1 && placeTwo == items.size()) {
			openNextActivity();

		} else {
			// If it's not the first load, animate button change
			if(placeOne != 0 || placeTwo != 1) {
				animateButtons();
			}

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


	public void animateButtons() {
		itemOne.startAnimation(fade);
		itemTwo.startAnimation(fade);
	}


	public void openNextActivity() {
		Intent i = new Intent(getBaseContext(), ResultsList.class);
		i.putExtra("ITEMS",  priorities);
		startActivity(i);
	}


	public void initializeComponents() {
		// Set ActionBar Color
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0d5c92")));

		fade = AnimationUtils.loadAnimation(this.getBaseContext(), R.anim.abc_fade_in);

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
