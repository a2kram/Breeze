package akhamd.breeze;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.net.URL;
import java.net.URLConnection;

/**
 * Created by akhamd on 10/8/2017.
 */

public class DataSync
{
    public class GetImage extends AsyncTask<Object, Object, Object>
    {
        private String requestUrl;
        private ImageView view;
        private Bitmap bitmap;

        public GetImage(String requestUrl, ImageView view)
        {
            this.requestUrl = requestUrl;
            this.view = view;
        }

        @Override
        protected Object doInBackground(Object... objects)
        {
            try
            {
                URL url = new URL(requestUrl);
                URLConnection conn = url.openConnection();
                bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            }
            catch (Exception ex)
            {
                Log.d("Hiiiiiiiiiiiiiiiiiiiiii", "failed " + ex);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o)
        {
            view.setImageBitmap(bitmap);
        }
    }
}
