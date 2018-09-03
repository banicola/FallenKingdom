package com.fk.main;

import org.bukkit.Bukkit;
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
            		for (Player p : Bukkit.getServer().getOnlinePlayers()){
            			if(Main.getPlayerTeam(p) == null) {
            				p.kickPlayer("Sorry, you were not in a team when the game launched !");
            			}
        				p.setLevel(time);
        				if(time <= 5 && !Main.gameStatus){
        					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10, 1);
        				}
        				if(time == 0 && Main.gameStatus){
        					p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 10, 1);
        				}
        			}
        			time--;
            	}
            }
        }, 0L, 20L);
    }
}
