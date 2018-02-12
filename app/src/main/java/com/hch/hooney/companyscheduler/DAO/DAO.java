package com.hch.hooney.companyscheduler.DAO;

import android.content.Context;
import android.util.Log;

import com.hch.hooney.companyscheduler.Recycler.Work.Work;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by hooney on 2018. 2. 2..
 */

public class DAO {
    public static User user;
    public static ArrayList<Work> wList;

    public static String Country;
    public static String Language;

    public static void init_static(){
        DAO.user = new User();
        DAO.user.setEid("00001");
        DAO.user.seteName("황철훈");
        DAO.user.setePhone("000-0000-0000");
        DAO.user.seteTeam("1_team");

        DAO.wList = new ArrayList<Work>();
        //temp
        DAO.wList.add(new Work("타이어 공기압 체크", 0.0, 0.0));
        DAO.wList.add(new Work("경고등 체크", 0.0, 0.0));
        DAO.wList.add(new Work("웨이트 확인", 0.0, 0.0));
        DAO.wList.add(new Work("현장1 위치 확인", 0.0, 0.0));
        DAO.wList.add(new Work("현장2 위치 확인", 0.0, 0.0));
    }

    public static void setCountryAndLanguage(Context mContext){
        Locale systemLocale = mContext.getResources().getConfiguration().locale;
        DAO.Country = systemLocale.getCountry();
        DAO.Language = systemLocale.getLanguage();

        //  KR : ko       US : en

        Log.d("DAO", DAO.Country + " / " + DAO.Language);
    }
}
