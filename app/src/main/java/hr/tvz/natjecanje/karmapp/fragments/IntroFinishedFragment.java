package hr.tvz.natjecanje.karmapp.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import hr.tvz.natjecanje.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFinishedFragment extends android.support.v4.app.Fragment {

    public IntroFinishedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro_finished, container, false);
    }

}
