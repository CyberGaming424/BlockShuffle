package com.cybergaming424.blockshuffle;

import com.cybergaming424.blockshuffle.commands.CommandHandler;
import com.cybergaming424.blockshuffle.listeners.BlockDetector;
import com.cybergaming424.blockshuffle.managers.ConfigManager;
import com.cybergaming424.blockshuffle.managers.PartyManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Blockshuffle extends JavaPlugin {

    private PartyManager partyManager;

    @Override
    public void onEnable() {
        ConfigManager.setUpConfig(this);

        partyManager = new PartyManager();

        Bukkit.getPluginManager().registerEvents(new BlockDetector(this), this);
        getCommand("blockshuffle").setExecutor(new CommandHandler(this));
    }

    public PartyManager getPartyManager(){
        return partyManager;
    }
}
