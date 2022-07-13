package com.cybergaming424.blockshuffle.instances;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Game {

    private List<UUID> players;
    private HashMap<UUID, Integer> roundsWon;
    private HashMap<UUID, Material> playersBlock;

    private Random ran;
    private int roundsToWin;

    public Game(List<UUID> players, int roundsToWin){
        this.players = players;
        roundsWon = new HashMap<>();
        this.roundsToWin = roundsToWin;

        ran = new Random();

        for(UUID uuid : players){
            roundsWon.put(uuid, 0);
        }
    }

    public void roundStart(){
        for(UUID uuid : players){
            playersBlock.put(uuid, Material.values()[ran.nextInt(Material.values().length-1)]);
        }
    }

    // Add points everytime a player wins a round
    public boolean addPoint(UUID player){

        roundsWon.replace(player, roundsWon.get(player) + 1);

        if(roundsWon.get(player) >= roundsToWin){
            return true;
        }

        roundStart();

        return false;
    }

    public Material getPlayersBlock(UUID uuid){
        return playersBlock.get(uuid);
    }

    public boolean hasPlayer(UUID player){
        return players.contains(player);
    }
}
