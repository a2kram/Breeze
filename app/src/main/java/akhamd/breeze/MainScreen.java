package akhamd.breeze;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


public class MainScreen extends AppCompatActivity
{
    // PagerAdapter to provide fragments for each sections
    // This keeps every loaded fragment in memory. If too memory intensive,
    // switch to FragmentStatePagerAdapter
    private SectionsPagerAdapter mSectionsPagerAdapter;

    // ViewPager to host section contents
    private ViewPager mViewPager;

    private FusedLocationProviderClient mFusedLocationClient;
    private static final int PERMISSION_REQUEST_CODE_LOCATION = 1;
    private static boolean mLocationGranted = false;
    private static boolean mLocationFound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        // Create adapter that returns a fragment for each activity primary sections
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up ViewPager with sections adapter
        mViewPager = (ViewPager) findViewById(R.id.MainContainer);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.MainTabs);
        tabLayout.setupWithViewPager(mViewPager);

        // Check if fine location access permission is granted to app
        if (AndroidCommon.checkPermission(getApplicationContext(),
            Manifest.permission.ACCESS_FINE_LOCATION))
        {
            mLocationGranted = true;
            mLocationFound = true;
        }
        else
        {
            // Request permission from user
            AndroidCommon.requestPermission(Manifest.permission.ACCESS_FINE_LOCATION,
                                            PERMISSION_REQUEST_CODE_LOCATION, getApplicationContext(),
                                            this, getString(R.string.location_request));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        switch (requestCode)
        {
            case PERMISSION_REQUEST_CODE_LOCATION:
            {
                mLocationGranted = (grantResults.length > 0 &&
                                    grantResults[0] == PackageManager.PERMISSION_GRANTED);
                mLocationFound = true;
            }
        }
    }

    // FragmentPagerAdapter that returns a fragment corresponding to a tab

    public class SectionsPagerAdapter extends FragmentPagerAdapter
    {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            // getItem is called to instantiate RestaurantListFragment for the given tab
            return RestaurantListFragment.newInstance(position + 1, mLocationFound);
        }

        @Override
        public int getCount()
        {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            switch (position)
            {
                case 0:
                    return getString(R.string.nearby_tab_label);
                case 1:
                    return getString(R.string.suggested_tab_label);
                case 2:
                    return getString(R.string.previous_tab_label);
            }

            return null;
        }
    }

    public void fetchLocationData(){
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                        }
                    }
                });
    }
}
