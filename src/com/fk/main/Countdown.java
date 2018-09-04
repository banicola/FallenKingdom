package com.fk.main;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Countdown extends JavaPlugin{
	
	static int time;
	static int TaskID;
	String game;
	
	public static void CountdownStart(int amount) {
        time = amount;
        
        TaskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Bukkit.getServer().getPluginManager().getPlugin("FallenKingdom"), new Runnable() {
            @Override
            public void run() {
            	if(time >= 0){
            		if(time == 0 && !Main.gameStatus) {
            			for(String t : Main.teams) {
            				int idx = new Random().nextInt(Main.playersTeam.get(t).size());
            				Main.teamLeader.put(t, Main.playersTeam.get(t).get(idx));
            			}
            		}
            		for (Player p : Bukkit.getServer().getOnlinePlayers()){
            			if(Main.getPlayerTeam(p) == null && Main.gameStatus) {
            				p.kickPlayer("Sorry, you were not in a team when the game launched !");
            			}
        				p.setLevel(time);
        				if(!Main.countdownStatus) {
        					p.setLevel(0);
        					Bukkit.getScheduler().cancelTask(TaskID);
        				}
        				if(time <= 5 && !Main.gameStatus){
        					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10, 1);
        				}
        				if(time == 0){
        					if(Main.gameStatus) {
        						p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 10, 1);
        						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "gamerule doDaylightCycle true");
        						Main.day=1;
        						Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE+"Day "+ChatColor.GREEN+Main.day);
        					} else {
        						Location spawn = new Location(Bukkit.getServer().getWorld(Main.config.getString("world")), Main.config.getInt("spawn.x"), Main.config.getInt("spawn.y"), Main.config.getInt("spawn.z"));
        						p.teleport(spawn);
        						p.getInventory().clear();
        						p.setGameMode(GameMode.SURVIVAL);
        						p.sendMessage(ChatColor.BLUE+"The "+ChatColor.WHITE+Main.getPlayerTeam(p)+ChatColor.BLUE+" team leader is "+ChatColor.WHITE+Main.teamLeader.get(Main.getPlayerTeam(p))+ChatColor.BLUE+" !");
        						Main.gameStatus = true;
        						Bukkit.getScheduler().cancelTask(TaskID);
        						StartGame.LauchGame();
        					}
        					
        				}
        			}
        			time--;
            	}
            }
        }, 0L, 20L);
    }
}
