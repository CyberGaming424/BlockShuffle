package com.cybergaming424.blockshuffle;

import com.cybergaming424.blockshuffle.managers.ConfigManager;
import com.cybergaming424.blockshuffle.managers.PartyManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Blockshuffle extends JavaPlugin {

    private PartyManager partyManager;

    @Override
    public void onEnable() {
        ConfigManager.setUpConfig(this);

        partyManager = new PartyManager();

    }

    public PartyManager getPartyManager(){
        return partyManager;
    }
}
