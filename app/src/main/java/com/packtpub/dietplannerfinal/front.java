package com.packtpub.dietplannerfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

/*
import com.google.firebase.database.DatabaseReference;
*/

public class front extends AppCompatActivity {

    private TextView mTextMessage;
    private float x, y;
    static final int dis = 150;
    private int id;
    SessionRecord ss;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            switch (id=item.getItemId()) {
                case R.id.profile: {/*  ft.replace(R.id.content,new profile_frag());
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();
                    return true;*/
                    if (ss.isLoggedIn())
                    {
                        ft.replace(R.id.content, new aftlogin());
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        ft.commit();
                        return true;
                    }
                    else
                    {
                        ft.replace(R.id.content,new profile_frag());
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        ft.commit();

                        return true;
                    }
                }
                case R.id.daily:
                {
                    ft.replace(R.id.content,new DailyFragment());
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();
                    return true;
                }
                case R.id.what:
                {
                    ft.replace(R.id.content,new WhatFragment());
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();
                    return true;
                }
                case R.id.shopping:
                {
                    if (ss.isLoggedIn())
                    {
                        ft.replace(R.id.content, new aftloginshop());
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        ft.commit();
                        return true;
                    }
                    else
                    {
                        ft.replace(R.id.content,new ShoppingFragment());
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        ft.commit();

                        return true;
                    }   }
                case R.id.home:
                {
                    ft.replace(R.id.content,new HomeFragment());
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();
                    return true;
                }
            }
            return false;
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

        ss = new SessionRecord(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
