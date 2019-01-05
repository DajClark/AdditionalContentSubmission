package com.example.additionalcontentsubmission;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class GameList implements Serializable {

    // Implements static instance for Singleton Pattern.
    private static GameList gameListModel;

    // Declares ArrayList to hold game objects.
    private static ArrayList<Game> gameList;

    // returns single static instance of GameList class following singleton pattern.
    public static GameList get(Context context) {
        if (gameListModel == null) {
            gameListModel = new GameList(context);
        }
        return gameListModel;
    }

    // Constructor to initialise GameList class.
    private GameList(Context context) {

        // Initialises ArrayList to hold game objects.
        gameList = new ArrayList<>();
    }

    // Gets and returns a single game object from the list to the calling class.
    public Game getGame(UUID gameID) {

        for (Game game : gameList) {
            if(game.getGameID().equals(gameID)){
                return game;
            }
        }
        return null;
    }

    // Getter for the ArrayList gameList.
    public ArrayList<Game> getGames() {
        return gameList;
    }

    // Adds new game to the list.
    public void addGame(Game game){
        gameList.add(game);
    }

    // Method to iterate through list to find the games position and remove the game.
    public void removeGame(UUID gameID) {

        // Initialises position to -1 to accommodate 0 indexing.
        int position = -1;

        // Finds the position of the game to be deleted.
        for (Game game : gameList) {
            position += 1;
            if(game.getGameID().equals(gameID)){
                break;
            }
        }
        // Removes the game from the list.
        gameList.remove(position);
    }

    // Calls the serialize list method in the utility class.
    public static void saveData(Context context) {
        SerializeUtility.serializeObject(gameList,context);
    }

    // Calls the method to load serialized data into the list of games.
    public static void loadData(Context context) {
        gameList = SerializeUtility.deserializeObject(gameList,context);
    }

}