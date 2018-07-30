package com.octaviocarpes.customlistview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CustomListAdapter customListAdapter;
    ArrayList<String> todoList = new ArrayList<>();
    ListView listView;
    Button addTaskButton;
    private AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customListAdapter = new CustomListAdapter(this, todoList);
        listView = (ListView) findViewById(R.id.listViewID);
        listView.setAdapter(customListAdapter);
    }

    public void addTask(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Create Task");
        final EditText input = new EditText(this);
        builder.setView(input);
        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                todoList.add(input.getText().toString());
                customListAdapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                alert.dismiss();
            }
        });

        alert = builder.create();

        alert.show();
    }

    public void clearTask(View view){
        System.out.println(listView.getAdapter().getItemId(listView.getAdapter().getCount()));

    }
}
