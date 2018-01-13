package akhamd.breeze;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuListItemAdapter extends RecyclerView.Adapter<MenuListItemAdapter.ViewHolder>
{
    private ArrayList<MenuOption> mMenuItemsList;
    String mRestaurant;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name, price;
        ImageView thumbnail;
        MenuOption option;

        public ViewHolder(View view)
        {
            super(view);
            name = (TextView) view.findViewById(R.id.menu_item_name);
            price = (TextView) view.findViewById(R.id.menu_item_price);
            thumbnail = (ImageView) view.findViewById(R.id.menu_item_thumbnail);

            view.setOnClickListener(new View.OnClickListener()
            {
                @Override public void onClick(View v)
                {
                    Toast.makeText(v.getContext(), name.getText() + " added to order", Toast.LENGTH_SHORT).show();
                    User currentUser = Globals.getInstance().getUser();

                    currentUser.addToOrder(mRestaurant, option);
                }
            });
        }
    }

    // Provide a suitable constructor
    public MenuListItemAdapter(String Restaurant, ArrayList<MenuOption> ItemsList)
    {
        mRestaurant = Restaurant;
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
        holder.option = mMenuItemsList.get(position);
        holder.name.setText(holder.option.getName());
        holder.price.setText("$" + holder.option.getPrice());

        DataSync OuterClass = new DataSync();
        DataSync.GetImage task = OuterClass.new GetImage(holder.option.getThumbnailUrl(),
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
