package com.zebra.game;

import com.zebra.game.model.Player;

public interface FireBaseInterface {

    void FirstFirebaseTest();

    void SetOnValueChangeListener(DataHolderClass dataholder);

    void SetValueInDb(String target, String value);

    //Creates a game which takes in a player with name and score and adds to database stored with a unique gamepin
    void createGameDb(Player player, final DataHolderClass dataholder);

    //Checks if a gamepin written exist in the database
    void checkCodeDb(final String gameId, final DataHolderClass dataholder);

    //Adds a player to a existing game
    void createPlayerDb(final Player player, final DataHolderClass dataholder);

    //Updates high score list in the database
    void updateHighScore(final Player player, final DataHolderClass dataholder);

    //Sets the high score
    void setHSList(final DataHolderClass dataholder);
}