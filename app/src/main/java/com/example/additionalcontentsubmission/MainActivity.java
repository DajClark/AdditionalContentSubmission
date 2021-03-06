package com.example.additionalcontentsubmission;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set view using XML layout file.
        setContentView(R.layout.activity_main);

        // Get fragment manager and begin fragment transactions.
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null){
            GameListFragment gameListFragment = new GameListFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, gameListFragment)
                    .commit();
        }

    }

}