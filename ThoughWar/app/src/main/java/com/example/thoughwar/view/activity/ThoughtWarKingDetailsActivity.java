package com.example.thoughwar.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.thoughwar.R;
import com.example.thoughwar.constants.Constants;
import com.example.thoughwar.model.ThoughtWarKingDataModel;
import com.example.thoughwar.view.custom.FontTextView;

public class ThoughtWarKingDetailsActivity extends AppCompatActivity
{
    private final static String TAG = ThoughtWarKingDetailsActivity.class.getSimpleName();

    private ThoughtWarKingDataModel mCurrentItem;

    private FontTextView mKingNameFontTV;
    private FontTextView mKingRatingFontTV;
    private FontTextView mBattleWonFontTV;
    private FontTextView mBattleLossFontTV;
    private FontTextView mStrengthFontTV;
    private FontTextView mStrengthTypeFontTV;
    private FontTextView mBackButtonFontTV;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_though_war_king_details);

        initViews();
    }


    ///
    // Provide initialization of View Components in details activity
    ///
    private void initViews()
    {
        mCurrentItem            =   getIntent().getParcelableExtra(Constants.CURRENT_ITEM);

        mKingNameFontTV         =   (FontTextView)  findViewById(R.id.king_name);
        mKingRatingFontTV       =   (FontTextView)  findViewById(R.id.rate_text);
        mBattleWonFontTV        =   (FontTextView)  findViewById(R.id.battle_won_score);
        mBattleLossFontTV       =   (FontTextView)  findViewById(R.id.battle_loss_score);
        mStrengthFontTV         =   (FontTextView)  findViewById(R.id.strength_text);
        mStrengthTypeFontTV     =   (FontTextView)  findViewById(R.id.battle_type_text);

        mKingNameFontTV.setText(mCurrentItem.mName);
        mKingRatingFontTV.setText(getString(R.string.rating_text) + " : "+mCurrentItem.mRating);
        mBattleWonFontTV.setText(mCurrentItem.mWinCount);
        mBattleLossFontTV.setText(mCurrentItem.mLossCount);
        mStrengthFontTV.setText(mCurrentItem.mStrength);
        mStrengthTypeFontTV.setText(mCurrentItem.mBattleType);
    }
}
