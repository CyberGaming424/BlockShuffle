package com.cybergaming424.blockshuffle.managers;

import com.cybergaming424.blockshuffle.instances.Game;
import com.cybergaming424.blockshuffle.instances.Party;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PartyManager {

    List<Party> gameParties;

    public PartyManager(){
        gameParties = new ArrayList<>();
    }

    // Creates a new game party
    public void newParty(UUID partyLead){
        int ID = gameParties.size() + 1;
        gameParties.add(new Party(ID, partyLead));
    }

    // Removes game party

    public void removeParty(int ID){
        for(Party party : gameParties){
            if(ID == party.getID()){
                gameParties.remove(party);
            }
        }
    }

    public Party getParty(UUID player){
        for(Party party : gameParties){
            if(party.getPlayers().contains(player)){
                return party;
            }
        }
        return null;
    }

    public Player getLead(UUID player){
        for(Party party : gameParties){
            if(party.getPartyLead().equals(player)){
                return Bukkit.getPlayer(player);
            }
        }
        return null;
    }

}
