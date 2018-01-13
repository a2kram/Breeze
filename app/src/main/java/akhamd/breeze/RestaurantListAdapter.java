package akhamd.breeze;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.ViewHolder>
{
    private ArrayList<Restaurant> mRestaurantList;
    Intent RestaurantSelected;
    Restaurant mRestaurant;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name, location;
        ImageView thumbnail;
        Restaurant restaurant = null;

        public ViewHolder(View view)
        {
            super(view);
            name = (TextView) view.findViewById(R.id.restaurant_name);
            location = (TextView) view.findViewById(R.id.restaurant_location);
            thumbnail = (ImageView) view.findViewById(R.id.restaurant_thumbnail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if(restaurant != null)
                    {
                        RestaurantSelected = new Intent(v.getContext(), RestaurantScreen.class);
                        RestaurantSelected.putExtra("restaurant", restaurant);
                        v.getContext().startActivity(RestaurantSelected);
                    }
                }
            });
        }
    }

    // Provide a suitable constructor
    public RestaurantListAdapter(ArrayList<Restaurant> RestaurantList)
    {
        mRestaurantList = RestaurantList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // Create new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_card, parent, false);

        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    // Replace contents of view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        mRestaurant = mRestaurantList.get(position);

        holder.name.setText(mRestaurant.getName());
        holder.location.setText(mRestaurant.getLocation());
        holder.restaurant = mRestaurant;

        DataSync OuterClass = new DataSync();
        DataSync.GetImage task = OuterClass.new GetImage(mRestaurant.getThumbnailUrl(),
                                                         holder.thumbnail);
        task.execute();
    }

    // Return size of mRestaurantList (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return mRestaurantList.size();
    }
}
