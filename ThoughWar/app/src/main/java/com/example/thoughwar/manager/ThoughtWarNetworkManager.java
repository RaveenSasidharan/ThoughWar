package com.example.thoughwar.manager;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;;
import com.example.thoughwar.ThoughtWarApplication;
import com.example.thoughwar.constants.ThoughtWarNetworkStatusCode;


import org.json.JSONArray;

/**
 * Created by Raveen S on 17-12-2016.
 */

public class ThoughtWarNetworkManager
{
    public interface ThoughWarNetworkManagerListener
    {
        void onRequestStatusReceived(int statusCode, String response);
    }


    private final static String TAG = ThoughtWarNetworkManager.class.getSimpleName();

    private ThoughWarNetworkManagerListener mListener;
    private Context mContext;

    public ThoughtWarNetworkManager(Context context)
    {
        mContext = context;
    }


    public void makeJSonArrayRequest(int method, String url, JSONArray reqObject, final ThoughWarNetworkManagerListener listener)
    {
        // mListener = listener;

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(method,url, reqObject,
                new Response.Listener<JSONArray>()
                {

                    @Override
                    public void onResponse(JSONArray response)
                    {
                        Log.d(TAG, response.toString());
                        listener.onRequestStatusReceived(ThoughtWarNetworkStatusCode.OK,response.toString());

                    }
                },
                new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        Log.e(TAG,"error is "+error.getMessage());
                        listener.onRequestStatusReceived(ThoughtWarNetworkStatusCode.ERROR, null);

                    }
                });


        //set retry policy
        int socketTimeout = 60000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjReq.setRetryPolicy(policy);

        RequestQueue requestQueue = ThoughtWarApplication.getInstance().getmRequestQueue(mContext);
        requestQueue.add(jsonObjReq);

    }// makeJSonArrayRequest
}
