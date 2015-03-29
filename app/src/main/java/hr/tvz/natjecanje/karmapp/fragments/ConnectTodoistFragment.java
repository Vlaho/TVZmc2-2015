package hr.tvz.natjecanje.karmapp.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import hr.tvz.natjecanje.R;
import hr.tvz.natjecanje.karmapp.KarmApp;
import hr.tvz.natjecanje.karmapp.callbacks.OnTodoistConnectedListener;
import hr.tvz.natjecanje.karmapp.utils.Keys;
import hr.tvz.natjecanje.karmapp.utils.TinyDB;
import hr.tvz.natjecanje.karmapp.utils.Utils;
import hr.tvz.natjecanje.karmapp.wrappers.todoist.TodoistToken;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnTodoistConnectedListener} interface
 * to handle interaction events.
 */
public class ConnectTodoistFragment extends DialogFragment {
    private static String TAG;

    private OnTodoistConnectedListener mListener;

    private EditText emailText;
    private EditText passwordText;

    public ConnectTodoistFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_connect_todoist, container, false);
        TAG = getClass().getName();

        getDialog().setTitle(getActivity().getResources().getString(R.string.connect_todoist_title));

        emailText = (EditText) rootView.findViewById(R.id.email);
        passwordText = (EditText) rootView.findViewById(R.id.password);

        Button submitButton = (Button) rootView.findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    public void onSubmit() {
        KarmApp.getTodoistService().authorize(emailText.getText().toString(), passwordText.getText().toString(),
                new Callback<TodoistToken>() {
                    @Override
                    public void success(TodoistToken todoistToken, Response response) {
                        TinyDB db = new TinyDB(getActivity());
                        db.putString(Keys.TODOIST_TOKEN, todoistToken.getToken());

                        if (mListener != null) {
                            mListener.onTodoistConnected();
                        }

                        getDialog().dismiss();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Utils.showAndLogError(getActivity(), TAG, error.getMessage());
                    }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnTodoistConnectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTodoistConnectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
