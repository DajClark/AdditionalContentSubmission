package com.example.additionalcontentsubmission;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class PagerActivity extends AppCompatActivity {

    // String to send and retrieve data in key value pair.
    private static final String EXTRA_GAME_ID = "game_id";

    private ViewPager mViewPager;
    private List<Game> mGames;

    // Intent to pass arguments from calling activity.
    public static Intent newIntent(Context packageContext, UUID gameId){
        Intent intent = new Intent(packageContext, PagerActivity.class);
        intent.putExtra(EXTRA_GAME_ID, gameId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        // Get arguments passed within intent.
        final UUID gameId = (UUID) getIntent().getSerializableExtra(EXTRA_GAME_ID);

        // Initialise view pager by ID from XML layout file.
        mViewPager = findViewById(R.id.view_pager);

        // Get list of games.
        mGames = GameList.get(this).getGames();

        // Get fragment manager and begin fragment transactions.
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                // Call new game fragment and pass game ID.
                return GameEditFragment.newInstance(mGames.get(position).getGameID());
            }

            // Return item count for viewpager scroll limits.
            @Override
            public int getCount() {
                return mGames.size();
            }
        });

        // Set the current fragment shown within the pager.
        for (int i = 0; i < mGames.size(); i++){
            if (mGames.get(i).getGameID().equals(gameId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
