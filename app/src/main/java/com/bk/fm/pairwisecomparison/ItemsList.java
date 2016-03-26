package com.bk.fm.pairwisecomparison;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ItemsList extends AppCompatActivity {
//--------------------------------------------------------------------
//
//		Fields
//
//--------------------------------------------------------------------
	private SensorManager sensorManager;
	private Sensor accelerometer;
	private ShakeDetector shakeDetector;

	private NumberedListAdapter adapter;

	private ArrayList<String> items;
	private ListView itemList;
	private Button addButton;
	private TextView input;
	private Button next;
	private Animation fade;


//--------------------------------------------------------------------
//
//		Life Cycle Methods
//
//--------------------------------------------------------------------

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_items_list);

		initializeComponents();
		addActionHandlers();
	}


	@Override
	public void onResume() {
		super.onResume();
		// Register the Session Manager Listener onResume
		sensorManager.registerListener(shakeDetector, accelerometer, SensorManager.SENSOR_DELAY_UI);
	}


	@Override
	public void onPause() {
		// Unregister the Sensor Manager onPause
		sensorManager.unregisterListener(shakeDetector);
		super.onPause();
	}

//--------------------------------------------------------------------
//
//		Logical Methods
//
//--------------------------------------------------------------------

	public void addActionHandlers() {
		//Shake Detection
		shakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

			@Override
			public void onShake(int count) {
				View root = findViewById(android.R.id.content);
				Snackbar snackbar = Snackbar.make(root, getString(R.string.clear_list_text), Snackbar.LENGTH_LONG)
						.setAction("CLEAR", new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								items.clear();
								updateList();
						}
					}
				);

				snackbar.show();
			}
		});

		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addButton.startAnimation(fade);
				updateList(input.getText().toString());
			}
		});

		next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				next.startAnimation(fade);
				if (items.size() >= 2) {
					Intent i = new Intent(getBaseContext(), Comparison.class);
					i.putExtra("ITEMS", items);
					startActivity(i);
				} else {
					Toast.makeText(getApplicationContext(), getString(R.string.warning1), Toast.LENGTH_SHORT).show();
				}
			}
		});

		itemList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				// Remove the item long-clicked from the list
				items.remove(position);

				updateList();

				// Return that the event was handled
				return true;
			}
		});

	}


	// Tell the adapter that its data has changed
	public void updateList() {
		if(adapter == null) {
			adapter = new NumberedListAdapter(getBaseContext(), items);
			itemList.setAdapter(adapter);

		} else {
			adapter.reloadData();
		}
	}


	// Add an element to the ListView
	public void updateList(String s) {
		if (!items.contains(s)) {
			items.add(s);

			updateList();

			input.setText("");
		}

	}

//--------------------------------------------------------------------
//
//		GUI Methods
//
//--------------------------------------------------------------------
	public void initializeComponents() {
		//Shake Detection
		sensorManager = (SensorManager) getBaseContext().getSystemService(getBaseContext().SENSOR_SERVICE);
		accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		shakeDetector = new ShakeDetector();

		// Set ActionBar Color
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0d5c92")));

		fade = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_in);

		itemList = (ListView) findViewById(R.id.itemList);
		addButton = (Button) findViewById(R.id.addButton);
		input = (TextView) findViewById(R.id.inputField);
		next = (Button) findViewById(R.id.nextButton);

		// Intent with items will exist in cases where the user went back from the results page
		items = (ArrayList<String>) getIntent().getSerializableExtra("ITEMS");

		if (items == null) {
			items = new ArrayList<>();

		}

		// Create the ListView's adapter
		updateList();

	}
} //End Class
