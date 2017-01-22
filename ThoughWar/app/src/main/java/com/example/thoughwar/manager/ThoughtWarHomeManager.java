package com.example.thoughwar.manager;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.example.thoughwar.constants.Constants;
import com.example.thoughwar.constants.ThoughtWarNetworkStatusCode;
import com.example.thoughwar.database.ThoughtWarDatabaseUtils;
import com.example.thoughwar.model.ThoughtWarBattleResponseModel;
import com.example.thoughwar.model.ThoughtWarKingDataModel;
import com.example.thoughwar.utils.ThoughtWarUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Raveen S on 07-01-2017.
 */

public class ThoughtWarHomeManager
{
    private final static String TAG =ThoughtWarHomeManager.class.getSimpleName();

    private Context mContext;
    private List<ThoughtWarBattleResponseModel> mBattleList;
    private List<ThoughtWarKingDataModel> mKingList;

    private ThoughtWarHomeManagerListener mListener;

    private ThoughtWarNetworkManager mNetworkManager;
    private ThoughtWarNetworkManager.ThoughWarNetworkManagerListener mNetworkManagerListener;


    public interface ThoughtWarHomeManagerListener
    {
        void onRequestCompleted(List<ThoughtWarKingDataModel> kingList);
    }

    public ThoughtWarHomeManager(Context context)
    {
        this.mContext   =   context;
        mNetworkManager =   new ThoughtWarNetworkManager(context);
        mBattleList   =   new ArrayList<>();

    }

    public void requestForKingsList(final ThoughtWarHomeManagerListener listener)
    {
        mListener   =   listener;
        mNetworkManagerListener =   new ThoughtWarNetworkManager.ThoughWarNetworkManagerListener()
        {
            @Override
            public void onRequestStatusReceived(int statusCode, String response)
            {
                if (ThoughtWarNetworkStatusCode.OK == statusCode)
                {
                    Log.d(TAG,"succes response:" +response);
                    try
                    {
                        JSONArray responseArray =   new JSONArray(response);
                        JSONObject kingObject;

                        //processing JSON Array for battles data
                        for (int index = 0; index < responseArray.length(); index++)
                        {
                            kingObject  =   responseArray.getJSONObject(index);
                            ThoughtWarBattleResponseModel currentItem = new ThoughtWarBattleResponseModel(kingObject);
                            mBattleList.add(currentItem);

                        }

                        processBattleItemList();

                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Log.e(TAG,"error response:" +response);
                }

            }
        };

        //requesting for war details list
        mNetworkManager.makeJSonArrayRequest(Request.Method.GET, Constants.GET_URL, null, mNetworkManagerListener);
    } //end requestForKingsList



    private void processBattleItemList()
    {
        //iterating through battles list to find kings rating
        for(int index = 0; index < mBattleList.size(); index++)
        {
            ThoughtWarUtils.calculateKingRating(mBattleList.get(index),mContext);

        }
        mKingList = ThoughtWarDatabaseUtils.fetchKingItemDataFromLocalStorage(mContext);
        //passing kings list to home screenn
        mListener.onRequestCompleted(mKingList);
    }

}
