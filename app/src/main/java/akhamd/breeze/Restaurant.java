package akhamd.breeze;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by akhamd on 10/7/2017.
 */

public class Restaurant implements Parcelable {
    private String mName;
    private String mLocation;
    private String mThumbnailUrl;
    private ArrayList<String> mMenuSectionLabels;
    private ArrayList<String> mMenuSectionThumbnailUrl;
    private int mRating;
    private int mMaxReservationNum;
    private boolean mAcceptsPayments;
    private boolean mAcceptsReservations;
    private boolean mAcceptsOrderAhead;
    private ArrayList<MenuOption> mAppetizerItems;
    private ArrayList<MenuOption> mMainItems;
    private ArrayList<MenuOption> mDessertItems;

    Restaurant(String name, String location, String thumbnail_url, int rating,
               boolean accepts_payments, boolean accepts_reservations,
               boolean accepts_order_ahead, ArrayList<String> menu_section_labels,
               ArrayList<String> menu_section_thumbnail_url, int max_reservation_num,
               ArrayList<MenuOption> appetizer_items, ArrayList<MenuOption> main_items,
               ArrayList<MenuOption> dessert_items)
    {
        mName = name;
        mLocation = location;
        mRating = rating;
        mThumbnailUrl = thumbnail_url;
        mAcceptsPayments = accepts_payments;
        mAcceptsReservations = accepts_reservations;
        mAcceptsOrderAhead = accepts_order_ahead;
        mMenuSectionLabels = menu_section_labels;
        mMenuSectionThumbnailUrl = menu_section_thumbnail_url;
        mMaxReservationNum = max_reservation_num;
        mAppetizerItems = appetizer_items;
        mMainItems = main_items;
        mDessertItems = dessert_items;
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

    public int getRating()
    {
        return mRating;
    }

    public boolean isAcceptsPayments()
    {
        return mAcceptsPayments;
    }

    public boolean isAcceptsReservations()
    {
        return mAcceptsReservations;
    }

    public boolean isAcceptsOrderAhead()
    {
        return mAcceptsOrderAhead;
    }

    public int getMenuSectionsCount()
    {
        return mMenuSectionLabels.size();
    }

    public String getMenuSectionLabel(int label)
    {
        return mMenuSectionLabels.get(label);
    }

    public ArrayList<MenuOption> getAppetizerItems()
    {
        return mAppetizerItems;
    }

    public ArrayList<MenuOption> getMainItems()
    {
        return mMainItems;
    }

    public ArrayList<MenuOption> getDessertItems()
    {
        return mDessertItems;
    }

    public String getMenuSectionThumbnailUrl(int label)
    {
        return mMenuSectionThumbnailUrl.get(label);
    }

    public int getMaxReservationNum()
    {
        return mMaxReservationNum;
    }

    protected Restaurant(Parcel in) {
        mName = in.readString();
        mLocation = in.readString();
        mThumbnailUrl = in.readString();
        if (in.readByte() == 0x01) {
            mMenuSectionLabels = new ArrayList<String>();
            in.readList(mMenuSectionLabels, String.class.getClassLoader());
        } else {
            mMenuSectionLabels = null;
        }
        if (in.readByte() == 0x01) {
            mMenuSectionThumbnailUrl = new ArrayList<String>();
            in.readList(mMenuSectionThumbnailUrl, String.class.getClassLoader());
        } else {
            mMenuSectionThumbnailUrl = null;
        }
        mRating = in.readInt();
        mMaxReservationNum = in.readInt();
        mAcceptsPayments = in.readByte() != 0x00;
        mAcceptsReservations = in.readByte() != 0x00;
        mAcceptsOrderAhead = in.readByte() != 0x00;
        if (in.readByte() == 0x01) {
            mAppetizerItems = new ArrayList<MenuOption>();
            in.readTypedList(mAppetizerItems, MenuOption.CREATOR);
        } else {
            mAppetizerItems = null;
        }
        if (in.readByte() == 0x01) {
            mMainItems = new ArrayList<MenuOption>();
            in.readTypedList(mMainItems, MenuOption.CREATOR);
        } else {
            mMainItems = null;
        }
        if (in.readByte() == 0x01) {
            mDessertItems = new ArrayList<MenuOption>();
            in.readTypedList(mDessertItems, MenuOption.CREATOR);
        } else {
            mDessertItems = null;
        }
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
        if (mMenuSectionLabels == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(mMenuSectionLabels);
        }
        if (mMenuSectionThumbnailUrl == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(mMenuSectionThumbnailUrl);
        }
        dest.writeInt(mRating);
        dest.writeInt(mMaxReservationNum);
        dest.writeByte((byte) (mAcceptsPayments ? 0x01 : 0x00));
        dest.writeByte((byte) (mAcceptsReservations ? 0x01 : 0x00));
        dest.writeByte((byte) (mAcceptsOrderAhead ? 0x01 : 0x00));
        if (mAppetizerItems == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeTypedList(mAppetizerItems);
        }
        if (mMainItems == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeTypedList(mMainItems);
        }
        if (mDessertItems == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeTypedList(mDessertItems);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Restaurant> CREATOR = new Parcelable.Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };
}