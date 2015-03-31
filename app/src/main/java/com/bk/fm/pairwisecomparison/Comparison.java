package com.bk.fm.pairwisecomparison;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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

		initializeComponents(savedInstanceState);

		addButtonHandlers();

	}

//--------------------------------------------------------------------
//
//		Logistical Methods
//
//--------------------------------------------------------------------

	public void addButtonHandlers() {

	}


//--------------------------------------------------------------------
//
//		GUI Methods
//
//--------------------------------------------------------------------

	public void initializeComponents(Bundle savedInstanceState) {
		items = (ArrayList<String>) getIntent().getSerializableExtra("ITEMS");

		progress = (ProgressBar) findViewById(R.id.progressBar);
		itemOne = (Button) findViewById(R.id.itemOne);
		itemTwo = (Button) findViewById(R.id.itemTwo);

		int n = items.size();
		totalComparisons = n * (n - 1) / 2;

		progress.setMax(totalComparisons);
		progress.setProgress(0);

	}

} //End Class
