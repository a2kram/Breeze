package akhamd.breeze;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.ViewHolder>
{
    private Restaurant mRestaurant;
    private String mSectionLabel;
    private Activity mActivity;

    public ArrayList<MenuOption> mMenuItems;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name;
        public ImageView thumbnail;
        public RecyclerView recyclerview;
        private boolean set = false;
        private RelativeLayout.LayoutParams reset_params;

        public ViewHolder(View view)
        {
            super(view);
            name = (TextView) view.findViewById(R.id.menu_type_name);
            thumbnail = (ImageView) view.findViewById(R.id.menu_type_thumbnail);
            recyclerview = (RecyclerView) view.findViewById(R.id.menuitems_recycler_view);
            reset_params = (RelativeLayout.LayoutParams)name.getLayoutParams();

            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                if (set)
                {
                    thumbnail.setVisibility(View.VISIBLE);
                    recyclerview.setVisibility(View.GONE);
                    name.setGravity(Gravity.END);
                    name.setLayoutParams(reset_params);
                }
                else
                {
                    thumbnail.setVisibility(View.GONE);
                    recyclerview.setVisibility(View.VISIBLE);

                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)name.getLayoutParams();
                    name.setLayoutParams(params);
                    name.setGravity(Gravity.CENTER);
                }

                set = !set;
                }
            });
        }
    }

    // Provide a suitable constructor
    public MenuListAdapter(Activity activity, Restaurant Restaurant)
    {
        mActivity = activity;
        mRestaurant = Restaurant;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // Create new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_type_card, parent, false);

        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    // Replace contents of view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        mSectionLabel = mRestaurant.getMenuSectionLabel(position);

        holder.name.setText(mSectionLabel);

        DataSync OuterClass = new DataSync();
        DataSync.GetImage task = OuterClass.new GetImage(mRestaurant.getMenuSectionThumbnailUrl(position),
                                                         holder.thumbnail);
        task.execute();

        switch(position)
        {
            case 0:
                mMenuItems = mRestaurant.getAppetizerItems();
                break;

            case 1:
                mMenuItems = mRestaurant.getMainItems();
                break;

            case 2:
                mMenuItems = mRestaurant.getDessertItems();
                break;
        }

        // Improves performance since changes in content do not change RecyclerView layout size
        holder.recyclerview.setHasFixedSize(true);

        // Use a linear layout manager
        mLayoutManager = new GridLayoutManager(mActivity, 2);
        holder.recyclerview.setLayoutManager(mLayoutManager);
        holder.recyclerview.setItemAnimator(new DefaultItemAnimator());

        // Specify an adapter
        mAdapter = new MenuListItemAdapter(mMenuItems);
        holder.recyclerview.setAdapter(mAdapter);
    }

    // Return size of mRestaurantList (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return mRestaurant.getMenuSectionsCount();
    }
}
