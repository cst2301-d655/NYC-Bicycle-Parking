package edu.cuny.citytech.nycbikeparking;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements LocationInputFragment.OnFragmentInteractionListener {

    private static final String LOCATION_DISPLAY_TAG = "locationDisplayFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // if we're being restored from a previous state,
        // then we don't need to do anything and should return or else
        // we could end up with overlapping fragments.
        if (savedInstanceState != null) {
            return;
        }

        // Create an instance of the fragment.
        LocationInputFragment locationInputFragment = new LocationInputFragment();

        // In case this activity was started with special instructions from an Intent,
        // pass the Intent's extras to the fragment as arguments
        locationInputFragment.setArguments(getIntent().getExtras());

        // Add the fragment to the 'fragment_container' FrameLayout
        getFragmentManager().beginTransaction()
                .add(R.id.fragment_container, locationInputFragment).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String location) {
        // The user sent the location.
        Log.d("main", "Sending location: " + location);

        // See if we have previously created the location display fragment.
        LocationDisplayFragment locationDisplayFragment =
                (LocationDisplayFragment) getFragmentManager().findFragmentByTag(LOCATION_DISPLAY_TAG);

        if (locationDisplayFragment != null) { // we have previously created it.
            // TODO Refresh the list.
        } else { // otherwise, we can swap fragments with a new one.
            locationDisplayFragment = LocationDisplayFragment.newInstance(location);

            // start the transaction.
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, locationDisplayFragment, LOCATION_DISPLAY_TAG);
            transaction.addToBackStack(null);

            // commit.
            transaction.commit();
        }
    }
}
