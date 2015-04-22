package hr.tvz.natjecanje.karmapp.activities;

import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hr.tvz.natjecanje.R;
import hr.tvz.natjecanje.karmapp.utils.Keys;
import hr.tvz.natjecanje.karmapp.utils.TinyDB;

public class SettingsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends PreferenceFragment {

        private List<String> connectedApps;
        private List<Preference> myPreference;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
            addPreferencesFromResource(R.xml.preferences);

            // Default donation type, value  1 (Once per month)
            ListPreference donationTypes = (ListPreference) findPreference("donationTypeList");
            donationTypes.setValueIndex(1);

            PreferenceCategory productivityApps = (PreferenceCategory) findPreference("productivityApps");

            TinyDB db = new TinyDB(getActivity());
            connectedApps = db.getList(Keys.CONNECTED_APPS);
            myPreference = new ArrayList<>();
            if (connectedApps.isEmpty())
            {
                Preference noAppsPreference = new Preference(getActivity());
                noAppsPreference.setTitle("No connected Apps");
                noAppsPreference.setSelectable(false);
                noAppsPreference.setSummary("Connect productivity Apps");

                productivityApps.addPreference(noAppsPreference);
            }
            else
            {
                for (int i = 0; i < connectedApps.size(); i++)
                {
                    myPreference.add(new Preference(getActivity()));
                    myPreference.get(i).setTitle(connectedApps.get(i));
                    myPreference.get(i).setKey(connectedApps.get(i));

                    productivityApps.addPreference(myPreference.get(i));
                }
            }

                return rootView;
            }

    }
}
