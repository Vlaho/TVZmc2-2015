package hr.tvz.natjecanje.karmapp.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.preference.Preference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hr.tvz.natjecanje.R;
import hr.tvz.natjecanje.karmapp.callbacks.OnCauseSelectedListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnCauseSelectedListener} interface
 * to handle interaction events.
 */
public class ChooseCauseFragment extends android.support.v4.app.Fragment {

    private OnCauseSelectedListener mListener;
    private ArrayAdapter<String> causesAdapter;

    public ChooseCauseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_choose_cause,container,false);

        String[] causes = getResources().getStringArray(R.array.donationCauses);

        List<String> causeList = new ArrayList<String>(Arrays.asList(causes));

        causesAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,causeList);

        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(causesAdapter);

        return rootView;



    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onCauseSelected();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnCauseSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnCauseSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
