package hr.tvz.natjecanje.karmapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import hr.tvz.natjecanje.R;
import hr.tvz.natjecanje.karmapp.callbacks.OnCauseSelectedListener;
import hr.tvz.natjecanje.karmapp.callbacks.OnDonationStyleSelectedListener;
import hr.tvz.natjecanje.karmapp.callbacks.OnMaxDonationSelectedListener;
import hr.tvz.natjecanje.karmapp.callbacks.OnProductivityAppConnectedListener;
import hr.tvz.natjecanje.karmapp.fragments.*;

import java.util.Locale;

public class IntroActivity extends ActionBarActivity implements OnCauseSelectedListener,
        OnDonationStyleSelectedListener, OnMaxDonationSelectedListener, OnProductivityAppConnectedListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intro, menu);
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
    public void onCauseSelected() {
        // TODO
    }

    @Override
    public void onStyleSelected() {
        // TODO
    }

    @Override
    public void onMaxDonationSelected() {
        // TODO
    }

    @Override
    public void onConnected() {
        // TODO
    }

    public void onFinished(View v) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            Fragment f = null;
            switch (position) {
                case 0:
                    f = new WelcomeFragment();
                    break;
                case 1:
                    f = new ConnectProductivityAppsFragment();
                    break;
                case 2:
                    f = new ChooseCauseFragment();
                    break;
                case 3:
                    f = new ChooseDonationStyleFragment();
                    break;
                case 4:
                    f = new ChooseMaxDonationFragment();
                    break;
                case 5:
                    f = new IntroFinishedFragment();
                    break;
            }

            return f;
        }

        @Override
        public int getCount() {
            // Show 6 total pages.
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
                case 3:
                    return getString(R.string.title_section4).toUpperCase(l);
                case 4:
                    return getString(R.string.title_section4).toUpperCase(l);
                case 5:
                    return getString(R.string.title_section5).toUpperCase(l);
            }
            return null;
        }
    }

}
