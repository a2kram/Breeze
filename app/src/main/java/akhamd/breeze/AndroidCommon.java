package akhamd.breeze;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by akhamd on 10/8/2017.
 */

public class AndroidCommon
{
    public static boolean checkPermission(Context _c, String strPermission)
    {
        int result = ContextCompat.checkSelfPermission(_c, strPermission);
        return (result == PackageManager.PERMISSION_GRANTED);
    }

    public static void requestPermission(String strPermission, int perCode, Context _c, Activity _a,
                                         String strRequest)
    {
        if (ActivityCompat.shouldShowRequestPermissionRationale(_a, strPermission))
        {
            Toast.makeText(_c, strRequest, Toast.LENGTH_LONG).show();
        }

        ActivityCompat.requestPermissions(_a, new String[]{strPermission}, perCode);
    }
}
