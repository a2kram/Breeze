package akhamd.breeze;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Restaurant fragment containing a simple view
 */
public class MenuListFragment extends Fragment
{
    private Restaurant mRestaurant = null;

    // Recycler view to host menu type cards
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public MenuListFragment() {}

    public static MenuListFragment newInstance()
    {
        return new MenuListFragment();
    }

    public void setRestaurant (Restaurant restaurant)
    {
        mRestaurant = restaurant;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_menu_list, container, false);

        mRecyclerView = rootView.findViewById(R.id.menu_recycler_view);

        // Improves performance since changes in content do not change RecyclerView layout size
        mRecyclerView.setHasFixedSize(true);

        // Use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // Specify an adapter
        mAdapter = new MenuListAdapter(getActivity(), mRestaurant);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }
}