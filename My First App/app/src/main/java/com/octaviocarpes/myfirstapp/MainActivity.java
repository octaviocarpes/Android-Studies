package com.octaviocarpes.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TOTAL_COUNT = "total_count";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Show a toast
     * @param view -- the view that is clicked
     */
    public void toastMe(View view){
        // Toast myToast = Toast.makeText(this, message, duration);
        Toast myToast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT);
        myToast.show();
    }

    public void countMe(View view){
        TextView textView = findViewById(R.id.textView);

        String countString = textView.getText().toString();

        Integer count = Integer.parseInt(countString);

        count++;

        countString = count.toString();

        textView.setText(countString);
    }

    public void randomMe(){
        TextView textView = findViewById(R.id.textView);
        Integer count = Integer.parseInt(textView.getText().toString());

        Intent randomIntent = new Intent(this, SecondActivity.class);
        randomIntent.putExtra(TOTAL_COUNT, count);
        startActivity(randomIntent);
    }
}
