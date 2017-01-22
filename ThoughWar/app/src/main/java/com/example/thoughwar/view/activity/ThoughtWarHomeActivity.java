package com.example.thoughwar.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.thoughwar.R;
import com.example.thoughwar.manager.ThoughtWarHomeManager;
import com.example.thoughwar.manager.ThoughtWarHomeManager.ThoughtWarHomeManagerListener;
import com.example.thoughwar.model.ThoughtWarKingDataModel;
import com.example.thoughwar.view.adapter.ThoughtWarHomeRecyclerAdapter;

import java.util.List;

public class ThoughtWarHomeActivity extends AppCompatActivity
{
    private final static String TAG = ThoughtWarHomeActivity.class.getSimpleName();

    private RecyclerView mHomeRecyclerView;

    private ThoughtWarHomeManager mHomeManager;
    private List<ThoughtWarKingDataModel> mKingList;
    private ThoughtWarHomeRecyclerAdapter mRecyclerAapter;
    private ThoughtWarHomeManagerListener mHomeManagerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_though_war_home);
        initView();
    }


    private void initView()
    {
        mHomeRecyclerView   =   (RecyclerView)findViewById(R.id.home_recylcer_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        if(null != mHomeRecyclerView)
            mHomeRecyclerView.setLayoutManager(manager);

        mHomeManager    =   new ThoughtWarHomeManager(this);

        mHomeManagerListener    =   new ThoughtWarHomeManagerListener()
        {
            @Override
            public void onRequestCompleted(List<ThoughtWarKingDataModel> kingList)
            {
                mRecyclerAapter =   new ThoughtWarHomeRecyclerAdapter(kingList,ThoughtWarHomeActivity.this);
                mHomeRecyclerView.setAdapter(mRecyclerAapter);
            }
        };

        mHomeManager.requestForKingsList(mHomeManagerListener);

    } // end initView
}
