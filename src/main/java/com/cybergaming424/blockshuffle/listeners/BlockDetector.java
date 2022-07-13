package com.cybergaming424.blockshuffle.listeners;

import com.cybergaming424.blockshuffle.Blockshuffle;
import com.cybergaming424.blockshuffle.instances.Game;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.UUID;

public class BlockDetector implements Listener {

    private Blockshuffle blockshuffle;

    public BlockDetector(Blockshuffle blockshuffle){
        this.blockshuffle = blockshuffle;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        UUID uuid = e.getPlayer().getUniqueId();
        if (blockshuffle.getPartyManager()
                .getParty(uuid) != null) {
            Game game = blockshuffle.getPartyManager().getParty(uuid).getGame();
            if(game.getPlayersBlock(uuid) == e.getFrom().getBlock().getRelative(0,-1, 0).getType()){
                game.addPoint(uuid);
            }
        }
    }

}
