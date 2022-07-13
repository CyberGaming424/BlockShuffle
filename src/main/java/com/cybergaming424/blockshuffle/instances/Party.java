package com.cybergaming424.blockshuffle.instances;

import java.util.List;
import java.util.UUID;

public class Party {

    int ID;
    private List<UUID> players;
    private Game game;

    public Party(int ID){
        this.ID = ID;
    }

    public int getID(){
        return ID;
    }

    public List<UUID> getPlayers(){
        return players;
    }

    public Game getGame(){
        return game;
    }

    public void createGame(int roundsToWin){
        game = new Game(players, roundsToWin);
    }

}
