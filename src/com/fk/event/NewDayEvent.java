package com.fk.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.WorldEvent;

import com.fk.main.Main;

public class NewDayEvent implements Listener{
	@EventHandler
	public void WeatherChangeEvent(WeatherChangeEvent e) {
		e.setCancelled(true);
	}
	
	public void dayChangeEvent(WorldEvent e) {
		long time = e.getWorld().getTime();
		if(time==0 && Main.day>=1) {
			Main.day++;
			if(Main.day>1) {
				Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE+"Day "+ChatColor.GREEN+Main.day);
			}
		}
		if(Main.day==1 && time==22800) {
			for(String team : Main.teams) {
				try {
					Main.teamBase.get(team);
				} catch(Exception exception) {
					for(Player p : Main.playersTeam.get(team)) {
						p.sendMessage(ChatColor.RED+"You have to setup your base before the sunrise. ("+ChatColor.WHITE+"1"+ChatColor.RED+" minute left)");
					}
				}
				
			}
		}
		if(Main.day==2) {
			for(String team : Main.teams) {
				try {
					if(Main.teamBase.get(team).getBlockX() != 0) {}
				} catch (Exception exception) {
					Main.teamBase.put(team, Main.teamLeader.get(team).getLocation());
					for(Player p : Main.playersTeam.get(team)) {
						p.sendMessage(ChatColor.GREEN+"You waited too long :/\nBase has been set at your leader current location: "+ChatColor.WHITE+Main.teamBase.get(team).getBlockX()+" "+Main.teamBase.get(team).getBlockY()+" "+Main.teamBase.get(team).getBlockZ());
					}
				}
				
			}
		}
	}
}
