package com.example.swipevideo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.swipevideo.fragments.HomeFragment;
import com.example.swipevideo.fragments.PlaylistsFragment;
import com.example.swipevideo.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    HomeFragment homeFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getlocalbroadcaster();
        homeFragment=new HomeFragment();
        getFragmentPage(homeFragment);
         bottomNavigation = findViewById(R.id.nav_view);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                //Menantukan halaman Fragment yang akan tampil
                switch (item.getItemId()){
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;

                    case R.id.nav_search:
                        fragment = new SearchFragment();
                        break;

                    case R.id.nav_playlists:
                        fragment = new PlaylistsFragment();
                        break;
                }
                return getFragmentPage(fragment);
            }
        });

    }

    public void getlocalbroadcaster(){
        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(new BroadcastReceiver() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onReceive(Context context, Intent intent) {
                String status = intent.getStringExtra("status");
                if (status.equals("playing")){
                    bottomNavigation.getMenu().getItem(0).setIcon(R.drawable.ic_baseline_pause_24);
                    homeFragment.playerready();



                }
                else if (status.equals("pause")){
                    bottomNavigation.getMenu().getItem(0).setIcon(R.drawable.ic_baseline_play_arrow_24);
                }

            }
        }, new IntentFilter("musicplayer"));

    }

    //Menampilkan halaman Fragment
    private boolean getFragmentPage(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.page_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}