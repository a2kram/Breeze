package akhamd.breeze;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by akhamd on 10/7/2017.
 */


public class User implements Parcelable {
    private String mName;
    private String mLocation;
    private String mThumbnailUrl;
    private UserType mType;
    ArrayList<Order> mCurrentOrders;

    public enum UserType{
        GUEST, OWNER, ADMIN
    }

    User(String name, String location, String thumbnail_url, UserType type)
    {
        mName = name;
        mLocation = location;
        mThumbnailUrl = thumbnail_url;
        mType = type;
        mCurrentOrders = null;
    }

    public String getName()
    {
        return mName;
    }

    public String getLocation()
    {
        return mLocation;
    }

    public String getThumbnailUrl()
    {
        return mThumbnailUrl;
    }

    public UserType getType()
    {
        return mType;
    }

    public Order getOrder(String restaurant)
    {
        for(Order order : mCurrentOrders)
        {
            if(order.getRestaurant().equals(restaurant))
            {
                return order;
            }
        }

        return null;
    }

    public int addToOrder(String restaurant, MenuOption dish)
    {
        int result;

        if(mCurrentOrders == null)
        {
            mCurrentOrders = new ArrayList<Order>();
        }
        else
        {
            for(Order order : mCurrentOrders)
            {
                if(order.getRestaurant().equals(restaurant))
                {
                    return order.addToOrder(restaurant, dish);
                }
            }
        }

        Order order = new Order(restaurant);
        result = order.addToOrder(restaurant, dish);
        mCurrentOrders.add(order);

        return result;
    }

    protected User(Parcel in) {
        mName = in.readString();
        mLocation = in.readString();
        mThumbnailUrl = in.readString();
        mCurrentOrders = null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mLocation);
        dest.writeString(mThumbnailUrl);

        if (mCurrentOrders == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeTypedList(mCurrentOrders);
        }
    }

    @SuppressWarnings("unused")
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}