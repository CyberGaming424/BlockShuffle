package com.cybergaming424.blockshuffle.managers;

import com.cybergaming424.blockshuffle.Blockshuffle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private static FileConfiguration config;

    public static void setUpConfig(Blockshuffle blockshuffle){
        ConfigManager.config = blockshuffle.getConfig();
        blockshuffle.saveDefaultConfig();
    }

    public static int getCountdownSeconds() { return config.getInt("countdown-seconds"); }

}
