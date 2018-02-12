package com.hch.hooney.companyscheduler.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hch.hooney.companyscheduler.DAO.DAO;
import com.hch.hooney.companyscheduler.R;
import com.hch.hooney.companyscheduler.Recycler.Work.WorkAdapter;
import com.hch.hooney.companyscheduler.Service.GPS;
import com.hch.hooney.companyscheduler.Service.Location;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkFragment extends Fragment {
    private View view;
    private Button complete;
    private RecyclerView recyclerView;

    private ProgressDialog asyncDialog;

    //variable;
    private ArrayList<Boolean> DoneList;
    private double lat;  //위도
    private double lon;  //경도


    public WorkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_work, container, false);

        init();
        setEvent();
        setUi();

        return view;
    }

    private void init(){
        //variable
        asyncDialog = new ProgressDialog(getActivity());
        asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        asyncDialog.setMessage("위치 수신중");

        DoneList = new ArrayList<Boolean>();
        for(int i=0 ; i< DAO.wList.size() ; i++){
            DoneList.add(false);
        }

        //layout
        complete = (Button) view.findViewById(R.id.work_complete_BTN);
        recyclerView = (RecyclerView) view.findViewById(R.id.work_do_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
    }

    private void setEvent(){
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int countTrue = 0;
                for(int i = 0;i< DoneList.size();i++){
                    if(DoneList.get(i)==true){
                        countTrue+=1; //countTrue++;
                    }
                }

                if(countTrue == DoneList.size()){
                    runGPS();
                }else{
                    Toast.makeText(getContext(), "모든 일을 완수해주세요.",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void setUi(){
        recyclerView.setAdapter(new WorkAdapter(getContext(), DoneList));
    }

    private void runGPS(){
        asyncDialog.show();
        GPS gps = new GPS(getContext());
        if(gps.isGetLocation()){
            lat = gps.getLat();
            lon = gps.getLon();
            if(lat == 0.0 && lon == 0.0 ){
                Toast.makeText(getContext(), "GPS 셋팅중입니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            }else{
                Location location = new Location(getContext(), lat, lon);
                String areaResult = location.searchLocation();


                Toast.makeText(getContext(), "Address : "+areaResult,
                        Toast.LENGTH_SHORT).show();

            }
            asyncDialog.dismiss();
            gps.stopUsingGPS();
        }else{
            asyncDialog.dismiss();
            gps.showSettingAlert();
        }
    }
}
