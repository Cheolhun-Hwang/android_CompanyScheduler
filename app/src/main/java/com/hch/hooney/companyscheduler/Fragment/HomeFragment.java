package com.hch.hooney.companyscheduler.Fragment;

import android.content.pm.PackageManager;
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.hch.hooney.companyscheduler.R;

public class HomeFragment extends Fragment {
    private final String TAG = "HomeFragment";
    private final int SIGNAL_PERMISSON = 2001;

    private View view;

    private Button start_work;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home, container, false);

        init();
        setEvent();

        return view;
    }

    private void init(){
        start_work = (Button) view.findViewById(R.id.home_start_work_BTN);
    }

    private void setEvent(){
        start_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            checkDangerousPermissions();
            }
        });
    }

    private void checkDangerousPermissions() {
        String[] permissions = {//import android.Manifest;
                android.Manifest.permission.ACCESS_FINE_LOCATION,   //GPS 이용권한
                android.Manifest.permission.ACCESS_COARSE_LOCATION, //네트워크/Wifi 이용 권한
                android.Manifest.permission.INTERNET                 //인터넷 사용 권한
        };

        //권한을 가지고 있는지 체크
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(getActivity(), permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "권한있음");
            //
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_contain, new WorkFragment()).commit();
        } else {
            Log.d(TAG, "권한없음");

            ActivityCompat.requestPermissions(getActivity(), permissions, SIGNAL_PERMISSON);
        }
    }//end of checkDangerousPermissions



    // 사용자의 권한 확인 후 사용자의 권한에 대한 응답 결과를 확인하는 콜백 메소드
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        Log.d(TAG, "권한 : " + requestCode +" / " + SIGNAL_PERMISSON);
        if (requestCode == SIGNAL_PERMISSON) {
            Log.d(TAG, "권한 : " + grantResults.length +" / " + grantResults[0]);
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d(TAG, "권한 : " + grantResults[0]);
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(getApplicationContext(), permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "권한 승인");
                    //
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_contain, new WorkFragment()).commit();
                } else {
                    //Toast.makeText(getApplicationContext(), permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "권한 승인되지 않음.");
                    Toast.makeText(getContext(), "이 기능은 위치 정보가 필수입니다",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }//end of onRequestPermissionsResult
}
