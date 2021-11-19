package com.ridge.digitalreceiptreader.activity.home;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.util.NFCEnabledActivity;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

/**
 * Main Activity that holds the 4 fragements that will be used within the home
 * screen.
 * 
 * @author Sam Butler
 * @since November 18, 2021
 */
public class MainActivity extends NFCEnabledActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navView, navController);
    }
}