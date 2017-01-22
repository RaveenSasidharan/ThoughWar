package com.example.thoughwar.utils;

import android.content.Context;

import com.example.thoughwar.constants.Constants;
import com.example.thoughwar.database.ThoughtWarDatabaseUtils;
import com.example.thoughwar.model.ThoughtWarBattleResponseModel;
import com.example.thoughwar.model.ThoughtWarKingDataModel;

/**
 * Created by Raveen S on 07-01-2017.
 */

public class ThoughtWarUtils
{
    public static void calculateKingRating(ThoughtWarBattleResponseModel battleModel, Context context)
    {

        double r1, r2;
        double R1,R2;
        double E1,E2;
        double S1,S2;


        ThoughtWarKingDataModel attackerKingModel = ThoughtWarDatabaseUtils.returnKingData(context,ThoughtWarDatabaseUtils.KingsTable.TABLE_NAME,
                ThoughtWarDatabaseUtils.KingsTable.NAME,battleModel.mAttackerKing);
        //if null, attacker king is not present and initializing rating to default
        if(null == attackerKingModel)
        {
            r1 = Constants.BASE_RATING;
        }
        else
        {
            r1 = Float.valueOf(attackerKingModel.mRating);
        }

        //if null, defender king is not present and initializing rating to default
        ThoughtWarKingDataModel defenderKIngModel = ThoughtWarDatabaseUtils.returnKingData(context,ThoughtWarDatabaseUtils.KingsTable.TABLE_NAME,
                ThoughtWarDatabaseUtils.KingsTable.NAME,battleModel.mDefenderKing);

        if(null == defenderKIngModel)
        {
            r2 = Constants.BASE_RATING;
        }
        else
        {
            r2 = Float.valueOf(defenderKIngModel.mRating);
        }

        //computing transformed rating
        R1 = Math.pow(10,(r1/400));
        R2 = Math.pow(10,(r2/400));


        //calculating Expected score

        E1 = R1 / (R1 + R2);
        E2 = R2 / (R1 + R2);

        //calculating actual score based on attacker_outcome status
        //attacker_outcome : win, attacker wins
        //attacker_outcome : draw, battle draw
        //else defender wins

        if(Constants.WIN.equals(battleModel.mAttackerOutCome))
        {
            S1  =   1;
            S2  =   0;

            r1  =   r1 + Constants.K_FACTOR* (S1 - E1);
            r2  =   r2 + Constants.K_FACTOR* (S2 - E2);
        }
        else if(Constants.DRAW.equals(battleModel.mAttackerOutCome))
        {
            S1  =   0.5;
            S2  =   0.5;

            r1  =   r1 + Constants.K_FACTOR* (S1 - E1);
            r2  =   r2 + Constants.K_FACTOR* (S2 - E2);

        }
        else
        {
            S1  =   0;
            S2  =   1;

            r1  =   r1 + Constants.K_FACTOR* (S1 - E1);
            r2  =   r2 + Constants.K_FACTOR* (S2 - E2);

        }

        //if attacker king is not present in database,
        //inserting to database
        if(null == attackerKingModel )
        {
            attackerKingModel = new ThoughtWarKingDataModel();
            attackerKingModel.mName = battleModel.mAttackerKing;
            attackerKingModel.mRating = String.valueOf(r1);
            if(Constants.WIN.equals(battleModel.mAttackerOutCome)) //win and lose count is updated
            {
                attackerKingModel.mStrength =   "Attack";
                attackerKingModel.mBattleType = battleModel.mBattleType;
                attackerKingModel.mLossCount    =   "0";
                attackerKingModel.mWinCount =   "1";

            }
            else if(!(Constants.DRAW.equals(battleModel.mAttackerOutCome)))
            {
                attackerKingModel.mStrength =   "Defends";
                attackerKingModel.mBattleType = battleModel.mBattleType;
                attackerKingModel.mWinCount = "0";
                attackerKingModel.mLossCount = "1";
            }

            ThoughtWarDatabaseUtils.insertKingItemintoLocalStorage(context,attackerKingModel);

        }
        else //updating database , if king entry is already there in database
        {
            attackerKingModel.mRating = String.valueOf(r1);
            if(Constants.WIN.equals(battleModel.mAttackerOutCome))
            {
                attackerKingModel.mWinCount = String.valueOf(Integer.valueOf(attackerKingModel.mWinCount)+1);
                attackerKingModel.mStrength =   "Attack";
                attackerKingModel.mBattleType = battleModel.mBattleType;
            }
            else if(!(Constants.DRAW.equals(battleModel.mAttackerOutCome)))
            {
                attackerKingModel.mLossCount = String.valueOf(Integer.valueOf(attackerKingModel.mLossCount)+1);
                attackerKingModel.mStrength =   "Defends";
                attackerKingModel.mBattleType = battleModel.mBattleType;

            }

            ThoughtWarDatabaseUtils.updateKingItemIntoDataBase(context,attackerKingModel);

        }




        //if defender king is not present in database,
        //inserting to database
        if(null == defenderKIngModel )
        {
            defenderKIngModel = new ThoughtWarKingDataModel();
            defenderKIngModel.mName = battleModel.mAttackerKing;
            defenderKIngModel.mRating = String.valueOf(r1);
            if( !(Constants.WIN.equals(battleModel.mAttackerOutCome))
                 &&!(Constants.DRAW.equals(battleModel.mAttackerOutCome)) )
            {
                defenderKIngModel.mStrength =   "Attack";
                defenderKIngModel.mBattleType = battleModel.mBattleType;
                defenderKIngModel.mLossCount = "0";
                defenderKIngModel.mWinCount = "1";

            }
            else
            {
                defenderKIngModel.mStrength =   "Defends";
                defenderKIngModel.mBattleType = battleModel.mBattleType;
                defenderKIngModel.mLossCount = "1";
                defenderKIngModel.mWinCount = "0";
            }

            ThoughtWarDatabaseUtils.insertKingItemintoLocalStorage(context,attackerKingModel);

        }
        else //updating database , if king entry is already there in database
        {
            defenderKIngModel.mRating = String.valueOf(r1);
            if(!(Constants.WIN.equals(battleModel.mAttackerOutCome))
                    &&!(Constants.DRAW.equals(battleModel.mAttackerOutCome)) )
            {
                defenderKIngModel.mWinCount = String.valueOf(Integer.valueOf(defenderKIngModel.mWinCount)+1);
                defenderKIngModel.mStrength =   "Attack";
                defenderKIngModel.mBattleType = battleModel.mBattleType;
            }
            else
            {
                defenderKIngModel.mLossCount = String.valueOf(Integer.valueOf(defenderKIngModel.mLossCount)+1);
                defenderKIngModel.mStrength =   "Defends";
                defenderKIngModel.mBattleType = battleModel.mBattleType;

            }

            ThoughtWarDatabaseUtils.updateKingItemIntoDataBase(context,defenderKIngModel);
        }


    }



}
