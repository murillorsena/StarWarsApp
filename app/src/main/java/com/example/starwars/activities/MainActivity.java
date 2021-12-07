package com.example.starwars.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.starwars.R;
import com.example.starwars.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private BottomNavigationView bottomNavigation;
    private NavHostFragment navHostFragment;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        //bottomNavigation = binding.bottomNavigation;
        //bottomNavigation.setupWithNavController(navController);
        setContentView(binding.getRoot());

        // VERIFICAR
        /*
        if (savedInstanceState == null) {
            Fragment fragment = new HomeFragment();

            getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment, HomeFragment.TAG)
                .commit();
        }

         */

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        //Log.d(TAG, "OQ TEM AQ -> " + navHostFragment.getNavController().getNavInflater());

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch(item.getItemId()) {
                case R.id.home_fragment:
                    Log.d(TAG, "home");
                    return true;
                case R.id.apps_fragment:
                    Log.d(TAG, "apps");
                    return true;
                case R.id.profile_fragment:
                    Log.d(TAG, "profile");
                    //navController.navigate(R.layout.fragment_profile);
                    //  Navigation.findNavController(this).navigate(R.layout.fragment_profile);
                    return true;
                default:
                    return false;
            }
        });

        //Toolbar toolbar = binding;
        //AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();

        //NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        //NavigationUI.setupWithNavController(bottomNavigation, navController);

    }

}