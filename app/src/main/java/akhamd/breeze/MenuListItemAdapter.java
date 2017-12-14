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

public class MenuListItemAdapter extends RecyclerView.Adapter<MenuListItemAdapter.ViewHolder>
{
    private ArrayList<MenuOption> mMenuItemsList;
    private MenuOption mMenuOption;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name, price;
        public ImageView thumbnail;

        public ViewHolder(View view)
        {
            super(view);
            name = (TextView) view.findViewById(R.id.menu_item_name);
            price = (TextView) view.findViewById(R.id.menu_item_price);
            thumbnail = (ImageView) view.findViewById(R.id.menu_item_thumbnail);
        }
    }

    // Provide a suitable constructor
    public MenuListItemAdapter(ArrayList<MenuOption> ItemsList)
    {
        mMenuItemsList = ItemsList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // Create new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item_card, parent, false);

        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    // Replace contents of view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        mMenuOption = mMenuItemsList.get(position);

        holder.name.setText(mMenuOption.getName());
        holder.price.setText("$" + mMenuOption.getPrice());

        DataSync OuterClass = new DataSync();
        DataSync.GetImage task = OuterClass.new GetImage(mMenuOption.getThumbnailUrl(),
                                                         holder.thumbnail);
        task.execute();
    }

    // Return size of mRestaurantList (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        if(mMenuItemsList == null)
        {
            return 0;
        }

        return mMenuItemsList.size();
    }
}
