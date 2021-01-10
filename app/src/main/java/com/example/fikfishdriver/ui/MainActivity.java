package com.example.fikfishdriver.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.fikfishdriver.R;
import com.example.fikfishdriver.session.SystemDataLocal;
import com.example.fikfishdriver.ui.fragment.DelayedFragment;
import com.example.fikfishdriver.ui.fragment.DeliveryFragment;
import com.example.fikfishdriver.ui.fragment.HomeFragment;
import com.example.fikfishdriver.ui.fragment.ProfileFragment;
import com.example.fikfishdriver.ui.fragment.ReportFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    final Fragment fragmentHome = new HomeFragment();
    final Fragment fragmentDelivery = new DeliveryFragment();
    final Fragment fragmentDelay = new DelayedFragment();
    final Fragment fragmentReport = new ReportFragment();
    final Fragment fragmentProfile = new ProfileFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragmentHome;
    BottomNavigationView navigationView;
    FrameLayout containerFragment;
    TextView title;
    private SystemDataLocal systemDataLocal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.nav_view);
        containerFragment = findViewById(R.id.containerView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = findViewById(R.id.title);

        if(getSupportActionBar() != null ){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }



        if(savedInstanceState != null){
            switch (savedInstanceState.getInt("fragstate")){
                case R.id.menu_home:
                    active = fragmentHome;
                    fm.beginTransaction().add(R.id.containerView,fragmentDelay,fragmentDelay.getClass().getSimpleName()).hide(fragmentDelay).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentDelivery,fragmentDelivery.getClass().getSimpleName()).hide(fragmentDelivery).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentProfile,fragmentProfile.getClass().getSimpleName()).hide(fragmentProfile).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentReport,fragmentReport.getClass().getSimpleName()).hide(fragmentReport).commit();
                    break;

                case R.id.menu_delivery:
                    active = fragmentDelivery;
                    fm.beginTransaction().add(R.id.containerView,fragmentDelay,fragmentDelay.getClass().getSimpleName()).hide(fragmentDelay).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentHome,fragmentHome.getClass().getSimpleName()).hide(fragmentHome).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentProfile,fragmentProfile.getClass().getSimpleName()).hide(fragmentProfile).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentReport,fragmentReport.getClass().getSimpleName()).hide(fragmentReport).commit();
                    break;

                case R.id.menu_delay:
                    active = fragmentDelay;
                    fm.beginTransaction().add(R.id.containerView,fragmentDelivery,fragmentDelivery.getClass().getSimpleName()).hide(fragmentDelivery).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentHome,fragmentHome.getClass().getSimpleName()).hide(fragmentHome).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentProfile,fragmentProfile.getClass().getSimpleName()).hide(fragmentProfile).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentReport,fragmentReport.getClass().getSimpleName()).hide(fragmentReport).commit();
                    break;

                case R.id.menu_report:
                    active = fragmentReport;
                    fm.beginTransaction().add(R.id.containerView,fragmentDelivery,fragmentDelivery.getClass().getSimpleName()).hide(fragmentDelivery).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentHome,fragmentHome.getClass().getSimpleName()).hide(fragmentHome).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentProfile,fragmentProfile.getClass().getSimpleName()).hide(fragmentProfile).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentDelay,fragmentDelay.getClass().getSimpleName()).hide(fragmentDelay).commit();
                    break;

                case R.id.menu_account:
                    active = fragmentProfile;
                    fm.beginTransaction().add(R.id.containerView,fragmentDelivery,fragmentDelivery.getClass().getSimpleName()).hide(fragmentDelivery).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentHome,fragmentHome.getClass().getSimpleName()).hide(fragmentHome).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentReport,fragmentReport.getClass().getSimpleName()).hide(fragmentReport).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentDelay,fragmentDelay.getClass().getSimpleName()).hide(fragmentDelay).commit();
                    break;
            }
            fm.beginTransaction().add(R.id.containerView,active,active.getClass().getSimpleName()).commit();
        }else{
            fm.beginTransaction().add(R.id.containerView,fragmentReport,fragmentReport.getClass().getSimpleName()).hide(fragmentReport).commit();
            fm.beginTransaction().add(R.id.containerView,fragmentDelay,fragmentDelay.getClass().getSimpleName()).hide(fragmentDelay).commit();
            fm.beginTransaction().add(R.id.containerView,fragmentDelivery,fragmentDelivery.getClass().getSimpleName()).hide(fragmentDelivery).commit();
            fm.beginTransaction().add(R.id.containerView,fragmentProfile,fragmentProfile.getClass().getSimpleName()).hide(fragmentProfile).commit();
            fm.beginTransaction().add(R.id.containerView,fragmentHome,fragmentHome.getClass().getSimpleName()).commit();
        }

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.menu_home:
                        fm.beginTransaction().hide(active).show(fragmentHome).commit();
                        active = fragmentHome;
                        title.setText("Home");
                        return true;

                    case R.id.menu_delivery:
                        fm.beginTransaction().hide(active).show(fragmentDelivery).commit();
                        active = fragmentDelivery;
                        title.setText("Pengiriman Hari ini");
                        return  true;

                    case R.id.menu_delay:
                        fm.beginTransaction().hide(active).show(fragmentDelay).commit();
                        active = fragmentDelay;
                        title.setText("Pengiriman Tertunda");
                        return  true;

                    case R.id.menu_report:
                        fm.beginTransaction().hide(active).show(fragmentReport).commit();
                        active = fragmentReport;
                        title.setText("Riwayat Pengiriman");
                        return true;

                    case R.id.menu_account:
                        fm.beginTransaction().hide(active).show(fragmentProfile).commit();
                        active = fragmentProfile;
                        title.setText("Profil Saya");
                        return true;

                    default:return false;
                }
            }
        });
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putInt("fragstate",navigationView.getSelectedItemId());
        super.onSaveInstanceState(outState, outPersistentState);
    }
}