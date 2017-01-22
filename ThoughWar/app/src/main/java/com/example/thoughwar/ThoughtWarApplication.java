package com.example.thoughwar;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Raveen S on 07-01-2017.
 */

public class ThoughtWarApplication extends Application
{
    private static ThoughtWarApplication instance;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate()
    {
        super.onCreate();

    }

    public static ThoughtWarApplication getInstance()
    {
        if(instance==null)
        {
            instance=new ThoughtWarApplication();
        }
        return instance;

    }


    public RequestQueue getmRequestQueue(Context context)
    {
        if(null == mRequestQueue)
        {
            mRequestQueue = Volley.newRequestQueue(context);
        }

        return mRequestQueue;
    }


}
