package akhamd.breeze;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by akhamd on 10/7/2017.
 */

public class MenuOption implements Parcelable {
    private String mName;
    private float mPrice;
    private String mThumbnailUrl;

    MenuOption(String name, float price, String thumbnail_url)
    {
        mName = name;
        mPrice = price;
        mThumbnailUrl = thumbnail_url;
    }

    public String getName()
    {
        return mName;
    }

    public String getThumbnailUrl()
    {
        return mThumbnailUrl;
    }

    public float getPrice()
    {
        return mPrice;
    }

    protected MenuOption(Parcel in) {
        mName = in.readString();
        mPrice = in.readFloat();
        mThumbnailUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeFloat(mPrice);
        dest.writeString(mThumbnailUrl);
    }

    @SuppressWarnings("unused")
    public static final Creator<MenuOption> CREATOR = new Creator<MenuOption>() {
        @Override
        public MenuOption createFromParcel(Parcel in) {
            return new MenuOption(in);
        }

        @Override
        public MenuOption[] newArray(int size) {
            return new MenuOption[size];
        }
    };
}