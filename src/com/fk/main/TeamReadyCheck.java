package com.fk.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TeamReadyCheck extends JavaPlugin{
	public static void isTeamReady(Player p){
		int totalReady = 0;
		String team = Main.getPlayerTeam(p);
		
		if(!Main.playersTeam.get(team).isEmpty()) {
			for(Player player : Main.playersTeam.get(team)) {
				if(Main.playerStatus.get(player)) {
					totalReady++;
				}
			}
			if(Main.teamStatus.get(team) && totalReady<Main.config.getInt("min_team")) {
				Main.teamReady--;
				Main.teamStatus.put(team, false);
			} else if(!Main.teamStatus.get(team) && totalReady>=Main.config.getInt("min_team")) {
				Main.teamReady++;
				Main.teamStatus.put(team, true);
			}
		}
		
		if(Main.countdownStatus && Main.teamReady<2) {
			Main.countdownStatus = false;
			Bukkit.getScheduler().cancelTask(Countdown.TaskID);
			for (Player pl : Bukkit.getServer().getOnlinePlayers()){
    			pl.setLevel(0);
    		}
		} else {
			if(Main.teamReady>=2) {
				StartGame.LobbyCountdown();
				Main.countdownStatus = true;
			}
		}		
	}

}
