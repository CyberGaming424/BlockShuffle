package com.cybergaming424.blockshuffle.instances;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Party {

    int ID;
    private UUID partyLead;
    private List<UUID> players;
    private List<UUID> partyRequests;
    private Game game;

    public Party(int ID, UUID partyLead){
        this.partyLead = partyLead;
        this.ID = ID;
        partyRequests = new ArrayList<>();
    }

    // Party manipulation
    public void addRequest(UUID requestedPlayer){
        partyRequests.add(requestedPlayer);
    }

    public boolean hasOutstandingRequest(UUID player){
        return partyRequests.contains(player);
    }

    public void addPlayer(UUID player){
        partyRequests.remove(player);
        players.add(player);
    }

    public void createGame(int roundsToWin){
        game = new Game(players, roundsToWin);
    }

    // Party Info
    public int getID(){
        return ID;
    }

    public List<UUID> getPlayers(){
        return players;
    }

    public UUID getPartyLead(){
        return partyLead;
    }

    public Game getGame(){
        return game;
    }

}
