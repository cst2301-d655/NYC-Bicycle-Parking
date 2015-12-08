package edu.cuny.citytech.nycbikeparking;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LocationInputFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class LocationInputFragment extends Fragment {

    /**
     * Whatever is listening for events eminating from this fragment.
     */
    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_location_input, container, false);

        // Lookup the button.
        Button button = (Button) view.findViewById(R.id.submit_button);

        // Set an on click action listener.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    // Look up the edit text.
                    EditText editText = (EditText) view.findViewById(R.id.edit_message);

                    // Get the location from the edit text.
                    String location = editText.getText().toString();

                    // Inform the listener.
                    mListener.onFragmentInteraction(location);
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        /**
         * Called when the user enters the location for which to search bicycle parking.
         *
         * @param location The location to search for bicycle parking.
         */
        public void onFragmentInteraction(String location);
    }

}
