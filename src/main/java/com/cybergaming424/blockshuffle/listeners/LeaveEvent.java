package com.cybergaming424.blockshuffle.listeners;

import com.cybergaming424.blockshuffle.Blockshuffle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class LeaveEvent implements Listener {

    private Blockshuffle blockshuffle;

    public LeaveEvent(Blockshuffle blockshuffle){
        this.blockshuffle = blockshuffle;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent pe){
        UUID player = pe.getPlayer().getUniqueId();
        if(blockshuffle.getPartyManager().getParty(player) != null){
            if(blockshuffle.getPartyManager().getParty(player).getPartyLead() == player){
                blockshuffle.getPartyManager().getParty(player).leadLeft();
                blockshuffle.getPartyManager().getParty(player).getPlayers().remove(player);

            }else{
                blockshuffle.getPartyManager().getParty(player).getPlayers().remove(player);
            }
        }

    }

}
