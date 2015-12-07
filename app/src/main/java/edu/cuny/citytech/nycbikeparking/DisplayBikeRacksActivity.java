package edu.cuny.citytech.nycbikeparking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

public class DisplayBikeRacksActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get the intent.
        Intent intent = getIntent();

        //Get the location from the intent.
        String location = intent.getStringExtra(MainActivity.LOCATION_KEY);
        Log.d("intent", "Location: " + location); //log it.

        //Create the text view.
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(getString(R.string.display_location_text) + ": " + location);

        //Set the new view as the content's view.
        setContentView(textView);
    }

}