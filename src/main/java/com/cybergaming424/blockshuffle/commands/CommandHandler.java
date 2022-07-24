package com.cybergaming424.blockshuffle.commands;

import com.cybergaming424.blockshuffle.Blockshuffle;
import com.cybergaming424.blockshuffle.instances.Party;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {

    Blockshuffle blockshuffle;

    public CommandHandler(Blockshuffle blockshuffle){
        this.blockshuffle = new Blockshuffle();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            return false;
        }

        if(args.length > 0){
            // Create party
            if(args[0].equalsIgnoreCase("createparty") && blockshuffle.getPartyManager().getParty(((Player) sender).getUniqueId()) == null){
                blockshuffle.getPartyManager().newParty(((Player) sender).getUniqueId());
                return false;
            }
            // Send invite
            if((args[0].equalsIgnoreCase("invite") && args[1] != null) &&
                    blockshuffle.getPartyManager().getParty(((Player) sender).getUniqueId()) == null){
                if(Bukkit.getPlayer(args[1]).isOnline()) {
                    blockshuffle.getPartyManager().getParty(((Player) sender).getUniqueId()).addRequest(Bukkit.getPlayer(args[1]).getUniqueId());
                    Bukkit.getPlayer(args[1]).sendMessage(((Player) sender).getDisplayName() + " has invited you to their party." +
                            " To accept use the command /blockshuffle accept");
                    return false;
                }else{
                    ((Player) sender).getPlayer().sendMessage(ChatColor.RED + "Either the player you invite is not online, or" +
                            " that username is invalid!");
                    return false;
                }
            }
            // Send request
            if((args[0].equalsIgnoreCase("request") && args[1] != null) &&
                    blockshuffle.getPartyManager().getParty(((Player) sender).getUniqueId()) == null){ // Checks if player is in a party
                if(blockshuffle.getPartyManager().getLead(Bukkit.getPlayer(args[1]).getUniqueId())!= null) {  // Checks if the player who's party this requested player is requesting from is the lead of the party.
                    blockshuffle.getPartyManager().getParty(Bukkit.getPlayer(args[1]).getUniqueId()).addRequest(((Player) sender).getUniqueId());
                    Bukkit.getPlayer(args[1]).sendMessage(ChatColor.GOLD + ((Player) sender).getDisplayName() + " has requested to join your party. " +
                            "/blockshuffle accept <player name> to accept or /blockshuffle deny <player name> to deny the request.");
                }else {
                    ((Player) sender).sendMessage( ChatColor.RED + "Player is not the lead of a party!");
                    return false;
                }
            }else {
                ((Player) sender).sendMessage(ChatColor.RED + "You are already in a party! /blockshuffle leave will allow you to leave" +
                        " your current party.");
                return false;
            }
            // Accept request
            if((args[0].equalsIgnoreCase("accept") && args[1] != null) &&
                    blockshuffle.getPartyManager().getParty(((Player) sender).getUniqueId()) != null){
                if(blockshuffle.getPartyManager().getLead(((Player) sender).getUniqueId()).equals(((Player) sender).getPlayer().getUniqueId())){
                    if(Bukkit.getPlayer(args[1]).isOnline() && blockshuffle.getPartyManager().getParty(((Player) sender).getUniqueId()).hasRequest(
                            Bukkit.getPlayer(args[1]).getUniqueId()
                    )){
                        blockshuffle.getPartyManager().getParty(((Player) sender).getUniqueId()).addPlayer(Bukkit.getPlayer(args[1]).getUniqueId());
                        blockshuffle.getPartyManager().getParty(((Player) sender).getUniqueId()).getPartyRequests().remove(Bukkit.getPlayer(args[1]).getUniqueId());
                        Bukkit.getPlayer(args[1]).sendMessage(ChatColor.GOLD + "You have been added to " + ((Player) sender).getDisplayName() + "'s party!");
                        sender.sendMessage(ChatColor.GOLD + "You have added " + args[1] + " to your party!");
                    }
                }else {
                    ((Player) sender).sendMessage(ChatColor.RED + "You are not the lead of the party! /blockshuffle createparty");
                    return false;
                }
            }
            // Deny request
            if((args[0].equalsIgnoreCase("deny") && args[1] != null) &&
                    blockshuffle.getPartyManager().getParty(((Player) sender).getUniqueId()) != null){
                if(blockshuffle.getPartyManager().getLead(((Player) sender).getUniqueId()).equals(((Player) sender).getPlayer().getUniqueId())){
                    if(Bukkit.getPlayer(args[1]).isOnline() && blockshuffle.getPartyManager().getParty(((Player) sender).getUniqueId()).hasRequest(
                            Bukkit.getPlayer(args[1]).getUniqueId()
                    )){
                        blockshuffle.getPartyManager().getParty(((Player) sender).getUniqueId()).getPartyRequests().remove(Bukkit.getPlayer(args[1]).getUniqueId());
                        Bukkit.getPlayer(args[1]).sendMessage(ChatColor.RED + "You were denied access to " + ((Player) sender).getDisplayName() + "'s party!");
                        sender.sendMessage(ChatColor.GOLD + "You denied " + args[1] + " to your party!");
                    }
                }else {
                    ((Player) sender).sendMessage(ChatColor.RED + "You are not the lead of the party! /blockshuffle createparty");
                    return false;
                }
            }


        }

        return false;
    }
}
