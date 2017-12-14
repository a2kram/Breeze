package akhamd.breeze;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


public class RestaurantScreen extends AppCompatActivity
{
    // PagerAdapter to provide fragments for each sections
    // This keeps every loaded fragment in memory. If too memory intensive,
    // switch to FragmentStatePagerAdapter
    private SectionsPagerAdapter mSectionsPagerAdapter;

    // ViewPager to host section contents
    private ViewPager mViewPager;

    private static boolean mLocationFound = false;
    private Restaurant mRestaurant = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_screen);

        mRestaurant = (Restaurant)getIntent().getParcelableExtra("restaurant");

        // Create adapter that returns a fragment for each activity primary sections
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up ViewPager with sections adapter
        mViewPager = (ViewPager) findViewById(R.id.RestaurantContainer);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.RestaurantTabs);
        tabLayout.setupWithViewPager(mViewPager);
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
            switch(position)
            {
                case 0:
                    MenuListFragment menuFragment = MenuListFragment.newInstance();
                    menuFragment.setRestaurant(mRestaurant);
                    return menuFragment;
                case 1:
                    ReserveFragment reserveFragment = ReserveFragment.newInstance();
                    reserveFragment.setRestaurant(mRestaurant);
                    return reserveFragment;
                case 2:
                    PayFragment payFragment= PayFragment.newInstance();
                    payFragment.setRestaurant(mRestaurant);
                    return payFragment;
                default:
                    return null;
            }
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
                    return getString(R.string.menu_tab_label);
                case 1:
                    return getString(R.string.reserve_tab_label);
                case 2:
                    return getString(R.string.pay_tab_label);
            }

            return null;
        }
    }
}
