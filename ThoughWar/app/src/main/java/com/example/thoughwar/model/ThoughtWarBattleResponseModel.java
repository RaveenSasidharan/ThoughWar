package com.example.thoughwar.model;

import org.json.JSONObject;

/**
 * Created by Raveen S on 07-01-2017.
 */

public class ThoughtWarBattleResponseModel
{
    public String mName;
    public String mYear;
    public String mBattleNumber;
    public String mAttackerKing;
    public String mDefenderKing;
    public String mAttackerOne;
    public String mAttackerTwo;
    public String mAttackerThree;
    public String mAttackerFour;
    public String mDefenderOne;
    public String mDefenderTwo;
    public String mDefenderThree;
    public String mDefenderFour;
    public String mAttackerOutCome;
    public String mBattleType;
    public String mMajorDeath;
    public String mMajorCapture;
    public String mAttackerSize;
    public String mDefenderSize;
    public String mAttackerComander;
    public String mDefenderCommander;
    public String mSummer;
    public String mLocation;
    public String mRegion;
    public String mNote;


    public ThoughtWarBattleResponseModel(JSONObject response)
    {
        mName               =   response.optString("name");
        mYear               =   response.optString("year");
        mBattleNumber       =   response.optString("battle_number");
        mAttackerKing       =   response.optString("attacker_king");
        mDefenderKing       =   response.optString("defender_king");
        mAttackerOne        =   response.optString("attacker_1");
        mAttackerTwo        =   response.optString("attacker_2");
        mAttackerThree      =   response.optString("attacker_3");
        mAttackerFour       =   response.optString("attacker_4");
        mDefenderOne        =   response.optString("defender_1");
        mDefenderTwo        =   response.optString("defender_2");
        mDefenderThree      =   response.optString("defender_3");
        mDefenderFour       =   response.optString("defender_4");
        mAttackerOutCome    =   response.optString("attacker_outcome");
        mBattleType         =   response.optString("battle_type");
        mMajorDeath         =   response.optString("major_death");
        mMajorCapture       =   response.optString("major_capture");
        mAttackerSize       =   response.optString("attacker_size");
        mDefenderSize       =   response.optString("defender_size");
        mAttackerComander   =   response.optString("attacker_commander");
        mDefenderCommander  =   response.optString("defender_commander");
        mSummer             =   response.optString("summer");
        mLocation           =   response.optString("location");
        mRegion             =   response.optString("region");
        mNote               =   response.optString("note");

    }
}
