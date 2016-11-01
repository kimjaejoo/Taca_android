package app.taca.com.myapplication;

import android.content.Context;

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
}
