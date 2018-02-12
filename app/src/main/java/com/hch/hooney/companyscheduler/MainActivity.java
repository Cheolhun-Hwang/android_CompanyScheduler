package com.hch.hooney.companyscheduler;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hch.hooney.companyscheduler.DAO.DAO;
import com.hch.hooney.companyscheduler.Fragment.HomeFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = "MainActivity";
    private final int SIGNAL_PERMISSON = 1001;

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("D-365");

        checkDangerousPermissions();

        init(toolbar);
        setEvent();
        setUi();
    }

    private void init(Toolbar toolbar){
        DAO.setCountryAndLanguage(getApplicationContext());

        //variable
        manager = getSupportFragmentManager();

        DAO.init_static();


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
    }

    private void setEvent(){
        drawer.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setUi(){
        switchFragment(new HomeFragment());
    }

    private void switchFragment(Fragment fragment){
        manager.beginTransaction().replace(R.id.frame_contain, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /* 사용자 권한 확인 메서드
          - import android.Manifest; 를 시킬 것
        */
    private void checkDangerousPermissions() {
        String[] permissions = {//import android.Manifest;
                android.Manifest.permission.ACCESS_FINE_LOCATION,   //GPS 이용권한
                android.Manifest.permission.ACCESS_COARSE_LOCATION, //네트워크/Wifi 이용 권한
                android.Manifest.permission.INTERNET                 //인터넷 사용 권한
        };

        //권한을 가지고 있는지 체크
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "권한있음");
            //
        } else {
            Log.d(TAG, "권한없음");

            ActivityCompat.requestPermissions(this, permissions, SIGNAL_PERMISSON);
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
                } else {
                    //Toast.makeText(getApplicationContext(), permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "권한 승인되지 않음.");

                }
            }
        }
    }//end of onRequestPermissionsResult
}
