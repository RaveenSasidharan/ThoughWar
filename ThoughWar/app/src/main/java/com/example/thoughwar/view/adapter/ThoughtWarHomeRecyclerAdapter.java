package com.example.thoughwar.view.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thoughwar.R;
import com.example.thoughwar.constants.Constants;
import com.example.thoughwar.model.ThoughtWarKingDataModel;
import com.example.thoughwar.view.activity.ThoughtWarKingDetailsActivity;
import com.example.thoughwar.view.custom.FontTextView;

import java.util.List;

/**
 * Created by Raveen S on 07-01-2017.
 */

public class ThoughtWarHomeRecyclerAdapter extends RecyclerView.Adapter<ThoughtWarHomeRecyclerAdapter.KingItemHolder>
{
    private List<ThoughtWarKingDataModel> mKingItemList;
    Context mContext;

    public ThoughtWarHomeRecyclerAdapter(List<ThoughtWarKingDataModel> mKingItemList, Context context)
    {
        this.mKingItemList = mKingItemList;
        this.mContext = context;
    }

    @Override
    public KingItemHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_king_item, parent, false);

        return new KingItemHolder(view);
    }

    @Override
    public void onBindViewHolder(final KingItemHolder holder, final int position)
    {
        holder.mKingName.setText(mKingItemList.get(position).mName);
        holder.mKingRating.setText(mContext.getString(R.string.rating_text) + " : "+mKingItemList.get(position).mRating);
        holder.mKingStrength.setText(mContext.getString(R.string.battle_strength_text) + " : "+mKingItemList.get(position).mBattleType);

        holder.mView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final Activity activity = (Activity) mContext;
                Intent naviIntent = new Intent(mContext, ThoughtWarKingDetailsActivity.class);
                naviIntent.putExtra(Constants.CURRENT_ITEM, mKingItemList.get(position));
                mContext.startActivity(naviIntent, ActivityOptions.makeSceneTransitionAnimation(activity, new Pair(holder.mView,"profileRepoImage")).toBundle());

            }
        });

    }

    @Override
    public int getItemCount()
    {
        return mKingItemList.size();
    }

    public class KingItemHolder extends RecyclerView.ViewHolder
    {
        public View mView;
        public FontTextView mKingName;
        public FontTextView mKingRating;
        public FontTextView mKingStrength;

        public KingItemHolder(View view)
        {
            super(view);
            mView           =   view;
            mKingName       =   (FontTextView) view.findViewById(R.id.king_name);
            mKingRating     =   (FontTextView) view.findViewById(R.id.rate_text);
            mKingStrength   =   (FontTextView) view.findViewById(R.id.battle_strength);

        }


    }// end KingItemHolder
}
