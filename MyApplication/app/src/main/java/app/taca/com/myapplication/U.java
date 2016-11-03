package app.taca.com.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Tacademy on 2016-11-01.
 */
public class U {
    private static U ourInstance = new U();

    public static U getInstance() {
        return ourInstance;
    }

    private U() {
    }
    RequestQueue queue;// = Volley.newRequestQueue( this );

    public RequestQueue getQueue(Context context) {
        if( queue == null )
            queue = Volley.newRequestQueue( context );
        return queue;
    }

    String STORE_NAME = "perf";
    public void setStoreString(Context context, String key, String value){
        SharedPreferences.Editor editor = context.getSharedPreferences(STORE_NAME, 0).edit();
        editor.putString(key, value);
        editor.commit();
    }
    public String getStoreString(Context context , String key){
        return context.getSharedPreferences(STORE_NAME, 0).getString(key, "");
    }
}
