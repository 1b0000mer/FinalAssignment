package com.liepu.finalassignment.third;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.liepu.finalassignment.R;
import com.liepu.finalassignment.third.fragments.HomeFragment;
import com.liepu.finalassignment.third.fragments.NoteFragment;

public class FragmentThird extends Fragment {
    BottomNavigationView navigationView;
    public static String URL = "https://sinka.lv/android_end_work.html";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();

        navigationView = view.findViewById(R.id.rv_navigation_simple);
        navigationView.setOnItemSelectedListener(getNavigation(appCompatActivity));
        startHomeFragment(appCompatActivity);
    }

    private NavigationBarView.OnItemSelectedListener getNavigation(AppCompatActivity appCompatActivity) {
        return new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.isChecked()) {
                    return false;
                }
                switch (item.getItemId()) {
                    case R.id.nav_home: {
                        startHomeFragment(appCompatActivity);
                        return true;
                    }
                    case R.id.nav_music: {
                        startNoteFragment(appCompatActivity);
                        return true;
                    }
                    case R.id.nav_search: {
                        startSearchFragment();
                        return true;
                    }
                    case R.id.nav_cal: {
                        startCalFragment();
                        return true;
                    }
                }
                return false;
            }
        };
    }

    private void startHomeFragment(AppCompatActivity appCompatActivity) {
        startFragment(new HomeFragment(), appCompatActivity);
    }

    private void startNoteFragment(AppCompatActivity appCompatActivity) {
        startFragment(new NoteFragment(), appCompatActivity);
    }

    private void startSearchFragment() {
    }


    private void startCalFragment() {

    }

    private void startFragment(Fragment fragment,AppCompatActivity appCompatActivity) {

        FragmentTransaction transaction = appCompatActivity.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        transaction.replace(R.id.rv_fragment_container, fragment);
        transaction.commit();
    }
}
