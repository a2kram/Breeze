package akhamd.breeze;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class Order implements Parcelable {
    private String mRestaurant;
    private float mTotalPrice;
    private Date mOrderDate;
    private ArrayList<MenuOption> mDishesOrdered;
    private boolean mComplete;

    Order(String restaurant)
    {
        mRestaurant = restaurant;
        mTotalPrice = 0;
        mOrderDate = null;
        mDishesOrdered = new ArrayList<MenuOption>();
        mComplete = false;
    }

    public String getRestaurant()
    {
        return mRestaurant;
    }

    public float getPrice()
    {
        return mTotalPrice;
    }

    public Date getDate()
    {
        return mOrderDate;
    }

    public String getOrderString()
    {
        String order = "";

        for (MenuOption option : mDishesOrdered)
        {
            order += option.getName() + "\n";
        }

        return order;
    }

    public ArrayList<MenuOption> getDishes()
    {
        return mDishesOrdered;
    }

    public boolean getComplete() { return mComplete; }

    public void setComplete() { mComplete = true; }

    public int addToOrder(String restaurant, MenuOption dish)
    {
        if(mComplete)
        {
            return -1;
        }

        if(!mRestaurant.equals(restaurant))
        {
            return -2;
        }

        mTotalPrice += dish.getPrice();
        mDishesOrdered.add(dish);
        mOrderDate = java.util.Calendar.getInstance().getTime();

        return 0;
    }

    protected Order(Parcel in) {
        mRestaurant = in.readString();
        mTotalPrice = in.readFloat();
        mOrderDate = null;
        mDishesOrdered = null;
        mComplete = false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mRestaurant);
        dest.writeFloat(mTotalPrice);
        dest.writeLong(mOrderDate.getTime());

        if (mDishesOrdered == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeTypedList(mDishesOrdered);
        }

        dest.writeByte((byte) (mComplete ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}