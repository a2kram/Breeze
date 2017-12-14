package akhamd.breeze;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Restaurant fragment containing a simple view
 */
public class RestaurantListFragment extends Fragment
{
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_LOCATION_AVAILABLE = "location_available";

    ArrayList<Restaurant> mRestaurantList;

    // Recycler view to host restaurant cards
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public RestaurantListFragment() {}

    // Returns new instance of this fragment for given section number
    public static RestaurantListFragment newInstance(int sectionNumber, boolean locationAvail)
    {
        RestaurantListFragment fragment = new RestaurantListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putBoolean(ARG_LOCATION_AVAILABLE, locationAvail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        int sectionNum = getArguments().getInt(ARG_SECTION_NUMBER);
        boolean locationAvail = getArguments().getBoolean(ARG_LOCATION_AVAILABLE);

        switch (sectionNum)
        {
            case 1:
            {
                if(locationAvail)
                {
                    mRestaurantList = getNearbyRestaurants();
                }
                break;
            }

            case 2:
            {
                mRestaurantList = getSuggestedRestaurants();
                break;
            }

            case 3:
            {
                mRestaurantList = getPrevRestaurants();
                break;
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_restaurant_list, container, false);

        mRecyclerView = rootView.findViewById(R.id.restaurant_recycler_view);

        // Improves performance since changes in content do not change RecyclerView layout size
        mRecyclerView.setHasFixedSize(true);

        // Use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // Specify an adapter
        mAdapter = new RestaurantListAdapter(mRestaurantList);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    private static ArrayList<Restaurant> getNearbyRestaurants()
    {
        // Get device location

        // Retrieve JSON of nearby restaurants

        // Create arraylist of nearby restaurants
        ArrayList<Restaurant> nearbyList = new ArrayList<Restaurant>();

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Appetizers");
        labels.add("Main Course");
        labels.add("Dessert");

        ArrayList<String> pics = new ArrayList<String>();
        pics.add("http://del.h-cdn.co/assets/17/24/980x490/landscape-1497581926-delish-watermelon-caprese-1.jpg");
        pics.add("https://realfood.tesco.com/media/images/VENISON-STEAKS-WITH-BLACKBERRY-472x310-0a47e435-084d-477b-be70-79359bb17bf7-0-1087x714.jpg");
        pics.add("http://del.h-cdn.co/assets/17/28/980x490/landscape-1499799606-delish-cookie-dough-lasagna-still001.jpg");

        MenuOption option1 = new MenuOption("Salad", 7, "https://i.ytimg.com/vi/MmOJLzJN6Cw/maxresdefault.jpg");
        MenuOption option2 = new MenuOption("Fries", 5, "http://img.taste.com.au/MudZOM3z/taste/2016/11/french-fries-87711-1.jpeg");
        MenuOption option3 = new MenuOption("Butter Chicken", 10, "https://i.ytimg.com/vi/a03U45jFxOI/maxresdefault.jpg");
        MenuOption option4 = new MenuOption("Shawarma", 8, "http://www.recipetineats.com/wp-content/uploads/2014/12/Chicken-Shawarma_4.jpg");
        MenuOption option5 = new MenuOption("Burger", 8, "http://www.seriouseats.com/recipes/assets_c/2015/07/20150728-homemade-whopper-food-lab-35-thumb-1500xauto-425129.jpg");
        MenuOption option6 = new MenuOption("Wings", 10, "https://www.buffalowildwings.com/globalassets/menuitems/10710004-fast-break-traditional-wings.png");
        MenuOption option7 = new MenuOption("Biryani", 12, "https://i0.wp.com/files.hungryforever.com/wp-content/uploads/2017/06/09121657/chicken-fry-biryani-recipes.jpg?fit=1280%2C720");
        MenuOption option8 = new MenuOption("Ice Cream", 4, "http://victoriabuzz.com/wp-content/uploads/2017/02/04-traits-ice-cream-chocolate.jpg");

        ArrayList<MenuOption> appetizers = new ArrayList<>();
        appetizers.add(option1);
        appetizers.add(option2);

        ArrayList<MenuOption> main = new ArrayList<>();
        main.add(option3);
        main.add(option4);
        main.add(option5);
        main.add(option6);
        main.add(option7);

        ArrayList<MenuOption> dessert = new ArrayList<>();
        dessert.add(option8);

        nearbyList.add(new Restaurant("Burgers and Gyros", "Redmond, WA",
                                      "https://scontent.fsnc1-1.fna.fbcdn.net/v/t31.0-8/16422807_1305184579542889_8893374022346477451_o.jpg?oh=27baecd9ac22afbbb871d5252eed91d8&oe=5A3F4F6C",
                                      5, true, true, true, labels, pics, 10, appetizers, main, dessert));
        nearbyList.add(new Restaurant("Inchins Bamboo Garden", "Redmond, WA",
                                      "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRcqRB6_9aztQZ3_ZKTcrSrJ3JBT8xc-OruNIHYoPHq6mpzfimiFA",
                                      4, true, true, true, labels, pics, 10, appetizers, main, dessert));
        nearbyList.add(new Restaurant("Garlic Crush", "Redmond, WA",
                                      "http://garliccrush.com/_images/logo.png",
                                      1, true, true, true, labels, pics, 10, appetizers, main, dessert));
        nearbyList.add(new Restaurant("Hyderabad House", "Redmond, WA",
                                      "https://media-cdn.tripadvisor.com/media/photo-s/05/4d/2f/ed/hyderabad-house.jpg",
                                      4, true, true, true, labels, pics, 10, appetizers, main, dessert));
        nearbyList.add(new Restaurant("Kanishka Indian Cuisine", "Redmond, WA",
                                      "https://afar-production.imgix.net/uploads/images/post_images/images/Knw7veajko/original_open-uri20121118-12157-1yvn4pi?1383799274?ixlib=rails-0.3.0&crop=entropy&fit=crop&h=719&w=954",
                                      4, true, true, true, labels, pics, 10, appetizers, main, dessert));

        return nearbyList;
    }

    private static ArrayList<Restaurant> getSuggestedRestaurants()
    {
        // Get device location

        // Retrieve JSON of suggested restaurants

        // Create arraylist of nearby restaurants
        ArrayList<Restaurant> suggestedList = new ArrayList<Restaurant>();

        suggestedList.add(new Restaurant("Burgers and Gyros", "Redmond, WA",
                                         "https://scontent.fsnc1-1.fna.fbcdn.net/v/t31.0-8/16422807_1305184579542889_8893374022346477451_o.jpg?oh=27baecd9ac22afbbb871d5252eed91d8&oe=5A3F4F6C",
                                         5, true, true, true, new ArrayList<String>(), new ArrayList<String>(), 10, new ArrayList<MenuOption>(), new ArrayList<MenuOption>(), new ArrayList<MenuOption>()));
        suggestedList.add(new Restaurant("Maza Grill", "Renton, WA",
                                         "http://mazagrill.co/wp-content/uploads/2016/04/1-Maza-Grill-Logo.png",
                                         5, true, true, true, new ArrayList<String>(), new ArrayList<String>(), 10, new ArrayList<MenuOption>(), new ArrayList<MenuOption>(), new ArrayList<MenuOption>()));
        suggestedList.add(new Restaurant("Umi Sake", "Seattle, WA",
                                         "http://www.umisake.com/umilogo2.jpg",
                                         5, true, true, true, new ArrayList<String>(), new ArrayList<String>(), 10, new ArrayList<MenuOption>(), new ArrayList<MenuOption>(), new ArrayList<MenuOption>()));

        return suggestedList;
    }

    private static ArrayList<Restaurant> getPrevRestaurants()
    {
        // Get user restaurant history

        // Create arraylist of nearby restaurants
        ArrayList<Restaurant> prevList = new ArrayList<Restaurant>();

        prevList.add(new Restaurant("Burgers and Gyros", "Redmond, WA",
                                    "https://scontent.fsnc1-1.fna.fbcdn.net/v/t31.0-8/16422807_1305184579542889_8893374022346477451_o.jpg?oh=27baecd9ac22afbbb871d5252eed91d8&oe=5A3F4F6C",
                                    5, true, true, true, new ArrayList<String>(), new ArrayList<String>(), 10, new ArrayList<MenuOption>(), new ArrayList<MenuOption>(), new ArrayList<MenuOption>()));

        return prevList;
    }
}