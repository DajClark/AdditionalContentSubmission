package com.example.additionalcontentsubmission;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import java.util.Date;
import java.util.UUID;

public class GameEditFragment extends Fragment {


    // Method to create new instance of object to pass arguments from activity.
    public static GameEditFragment newInstance(UUID gameID) {
        // Bundle for holding serialized arguments.
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_GAME_ID, gameID);

        // Create new fragment instance.
        GameEditFragment fragment = new GameEditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // Key to pass game type using bundle.
    final static String EXTRA_GAME_ID = "id";

    // Stores the ID of the game being passed by the activity.
    private UUID gameID;

    // Stores game object for reading and updating within the onCreateView call.
    private Game game;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        // Calls onCreate methods from super class.
        super.onCreate(savedInstanceState);

        // Gets the arguments passed by the calling Activity.
        Bundle args = getArguments();
        if (args != null) {
            gameID = (UUID) args.getSerializable(EXTRA_GAME_ID);
        }

        game = GameList.get(getActivity()).getGame(gameID);

        // Adds the options menu to view.
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_delete_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_game:

                GameList.get(getActivity()).removeGame(gameID);
                getActivity().finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflates the fragment view using the XML layout file.
        View view = inflater.inflate(R.layout.fragment_game_edit, container, false);

        // Initialises view elements using the id's within the XML layout file.
        final EditText gameTitle = view.findViewById(R.id.gameTitle);
        final EditText gamePlatform = view.findViewById(R.id.gamePlatform);
        final EditText gameDescription = view.findViewById(R.id.gameDescription);
        final Button dateButton = view.findViewById(R.id.completedDate);
        final CheckBox isComplete = view.findViewById(R.id.gameComplete);

        // Sets the edit box text to the games details.
        gameTitle.setText(game.getTitle());
        gamePlatform.setText(game.getPlatform());
        gameDescription.setText(game.getDescription());

        // Checks if the game is complete and sets button text accordingly.
        if (game.isComplete()) {
            dateButton.setText(game.getDateComplete().toString());
        }

        // Sets the checkbox on the games completion status.
        isComplete.setChecked(game.isComplete());

        // Listens for text changes in the edit boxes and sets the game's properties.
        gameTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This line is intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                game.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This line is intentionally left blank
            }
        });

        gamePlatform.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This line is intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                game.setPlatform(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This line is intentionally left blank
            }
        });

        gameDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This line is intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                game.setDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This line is intentionally left blank
            }
        });

        // Initialise listener for checkbox on checked change.
        isComplete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // Declares new date and updates view and properties based on checkbox status.
                if (isChecked) {
                    Date date = new Date();
                    dateButton.setText(date.toString());

                    game.setComplete(true);
                    game.setDateComplete(date);
                } else {
                    dateButton.setText("");
                    game.setComplete(false);
                }
            }
        });

        return view;
    }
}
