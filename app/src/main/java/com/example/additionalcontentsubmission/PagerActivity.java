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

    private static final String EXTRA_GAME_ID = "game_id";

    private ViewPager mViewPager;
    private List<Game> mGames;

    public static Intent newIntent(Context packageContext, UUID gameId){
        Intent intent = new Intent(packageContext, PagerActivity.class);
        intent.putExtra(EXTRA_GAME_ID, gameId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        final UUID gameId = (UUID) getIntent().getSerializableExtra(EXTRA_GAME_ID);

        mViewPager = findViewById(R.id.view_pager);

        mGames = GameList.get(this).getGames();

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Game game = mGames.get(position);
                return GameEditFragment.newInstance(mGames.get(position));
            }

            @Override
            public int getCount() {
                return mGames.size();
            }
        });

        for (int i = 0; i < mGames.size(); i++){
            if (mGames.get(i).getGameID().equals(gameId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
