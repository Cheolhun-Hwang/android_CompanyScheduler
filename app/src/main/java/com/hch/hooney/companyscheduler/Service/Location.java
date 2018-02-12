package com.hch.hooney.companyscheduler.Service;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.widget.Toast;

import com.hch.hooney.companyscheduler.DAO.DAO;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by hooney on 2018. 2. 6..
 */

public class Location {
    private Context mContext;
    private double mapX; //lat 경도
    private double mapY; //lon 위도

    public Location(Context mContext, double mapX, double mapY) {
        this.mContext = mContext;
        this.mapX = mapX;
        this.mapY = mapY;
    }

    public String searchLocation(){
        String result_addr="";

        List<Address> addressList = null;

        try{


            Geocoder gc = null;
            if(DAO.Language.equals("ko")){
                gc = new Geocoder(mContext, Locale.KOREA);
            }else{
                gc = new Geocoder(mContext, Locale.ENGLISH);
            }

            if(gc ==null){
                Toast.makeText(mContext, "한국이 아닙니다.", Toast.LENGTH_SHORT).show();
            }else{
                addressList = gc.getFromLocation(mapX, mapY, 1);
                if(addressList !=null){
                    for(int i = 0; i<addressList.size();i++){
                        Address outAddress = addressList.get(i);
                        int addrCount = outAddress.getMaxAddressLineIndex() +1;

                        StringBuffer outAddrStr = new StringBuffer();
                        for(int j=0;j<addrCount;j++){
                            outAddrStr.append(outAddress.getAddressLine(j));
                        }
                        result_addr = outAddrStr.toString();
                    }
                    Log.d("Location", "Now : "+result_addr);
                    return result_addr;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return "Fail";
    }
}
