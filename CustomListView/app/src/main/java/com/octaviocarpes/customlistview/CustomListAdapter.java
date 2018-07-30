package com.octaviocarpes.customlistview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter {

    //to reference the Activity
    private final Activity context;

    //to store the list of todo list
    private final ArrayList<String> toDos;

    public CustomListAdapter(Activity context, ArrayList<String> toDos){

        super(context,R.layout.listview_row , toDos);
        this.context = context;
        this.toDos = toDos;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listview_row, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView todoTextView = (TextView) rowView.findViewById(R.id.textViewID);

        //this code sets the values of the objects to values from the arrays
        todoTextView.setText(toDos.get(position));

        return rowView;
    }


}
