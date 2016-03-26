package com.bk.fm.pairwisecomparison;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Kellen on 3/26/2016.
 */
public class NumberedListElement extends RelativeLayout {

//-------------------------------------------------------------
//
//		Fields
//
//-------------------------------------------------------------

	TextView indexText;
	TextView contentText;

//-------------------------------------------------------------
//
//		Life Cycle Methods
//
//-------------------------------------------------------------

	public NumberedListElement(Context context) {
		super(context);
	}

	public NumberedListElement(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NumberedListElement(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

//-------------------------------------------------------------
//
//		Adapter Methods
//
//-------------------------------------------------------------

	// Inflates an instance of this class into the passed parent view
	public static NumberedListElement inflate(ViewGroup parent) {
		return (NumberedListElement) LayoutInflater.from(parent.getContext()).inflate(R.layout.numbered_list_element, parent, false);

	}


	// Initializes this instance
	public void initialize() {
		// Grab the UI elements
		indexText = (TextView) findViewById(R.id.index);
		contentText = (TextView) findViewById(R.id.content);

	}


	// Sets the list item index and the associated content
	public void setData(int index, String content) {
		if(contentText == null || indexText == null) {
			initialize();
		}

		indexText.setText("" + (index + 1) + ". ");
		contentText.setText(content);

	}
}
