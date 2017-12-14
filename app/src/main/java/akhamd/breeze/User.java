package akhamd.breeze;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by akhamd on 10/7/2017.
 */


public class User implements Parcelable {
    private String mName;
    private String mLocation;
    private String mThumbnailUrl;
    private UserType mType;

    public enum UserType{
        GUEST, OWNER, ADMIN
    }

    User(String name, String location, String thumbnail_url, UserType type)
    {
        mName = name;
        mLocation = location;
        mThumbnailUrl = thumbnail_url;
        mType = type;
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

    protected User(Parcel in) {
        mName = in.readString();
        mLocation = in.readString();
        mThumbnailUrl = in.readString();
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