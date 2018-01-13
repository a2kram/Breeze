package akhamd.breeze;

/**
 * Created by a2kram on 2018-01-10.
 */

public class Globals {
    private static Globals instance;

    private User mCurrentUser;

    private Globals(){}

    public void setUser(User user)
    {
        this.mCurrentUser = user;
    }

    public User getUser()
    {
        return this.mCurrentUser;
    }

    public static synchronized Globals getInstance()
    {
        if (instance == null)
        {
            instance = new Globals();
        }

        return instance;
    }
}
