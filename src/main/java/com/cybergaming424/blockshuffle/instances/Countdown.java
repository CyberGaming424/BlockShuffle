package com.cybergaming424.blockshuffle.instances;

import com.cybergaming424.blockshuffle.Blockshuffle;
import com.cybergaming424.blockshuffle.managers.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    private Blockshuffle blockshuffle;
    private Arena arena;
    private int countDownSeconds;

    public Countdown(Blockshuffle blockshuffle, Arena arena){
        this.blockshuffle = blockshuffle;
        this.arena = arena;
        this.countDownSeconds = ConfigManager.getCountdownSeconds();
    }

    public void start(){
        arena.setState(GameState.COUTNDOWN);
        runTaskTimer(blockshuffle, 0, 20);
    }

    @Override
    public void run() {
        if(countDownSeconds == 0){
            cancel();
            arena.start();
            return;
        }

        if(countDownSeconds <= 10 || countDownSeconds % 15 == 0){
            arena.sendMessage(ChatColor.GREEN + "Game will start in " + countDownSeconds +
                    " second" + (countDownSeconds == 1 ? "" : "s") + ".");
        }

        arena.sendTitle(ChatColor.GREEN.toString() + countDownSeconds + " seconds" + (countDownSeconds == 1 ? "" : "s"),
                ChatColor.GRAY + "until game starts");

        countDownSeconds--;
    }

}
