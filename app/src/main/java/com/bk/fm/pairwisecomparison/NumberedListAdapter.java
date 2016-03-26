package com.bk.fm.pairwisecomparison;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Kellen on 3/26/2016.
 */
public class NumberedListAdapter extends ArrayAdapter<String> {
//-------------------------------------------------------------
//
//		Fields
//
//-------------------------------------------------------------



//-------------------------------------------------------------
//
//		LifeCycle Methods
//
//-------------------------------------------------------------

	public NumberedListAdapter(Context context, ArrayList<String> objects) {
		super(context, 0, objects);
	}

//-------------------------------------------------------------
//
//		Adapter Overrides
//
//-------------------------------------------------------------


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Either recycle a view or make a new one
		NumberedListElement element = convertView == null ?
				NumberedListElement.inflate(parent) : (NumberedListElement) convertView;

		// Set the view's data
		element.setData(position, getItem(position));

		return element;

	}


	public void reloadData() {
		notifyDataSetChanged();
	}
}
