package com.example.additionalcontentsubmission;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

public class GameList {

    // Implements static instance for Singleton Pattern.
    private static GameList gameListModel;

    // Declares ArrayList to hold game objects.
    private ArrayList<Game> gameList;

    // returns single static instance of GameList class following singleton pattern.
    public static GameList get(Context context) {
        if (gameListModel == null) {
            gameListModel = new GameList(context);
        }
        return gameListModel;
    }

    // Constructor to initialise GameList class.
    private GameList(Context context){

        // Initialises ArrayList to hold game objects.
        gameList = new ArrayList<>();

        // Sample test data added to the collection.
        Game game1 = new Game("Smash Brothers", "Switch", "Fighting game for up to 8 players");
        gameList.add(game1);
        Game game2 = new Game("Skyrim", "PS3", "Single player fantasy role playing game");
        gameList.add(game2);
        Game game3 = new Game("Stardew Valley", "PC", "Single player country life simulator");
        gameList.add(game3);
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

    public void removeGame(UUID gameID) {
        for (Game game : gameList) {
            if(game.getGameID().equals(gameID)){
                gameList.remove(game);
            }
        }
    }

}