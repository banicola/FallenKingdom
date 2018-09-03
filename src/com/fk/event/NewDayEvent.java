package com.fk.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
			Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE+"Day "+ChatColor.GREEN+Main.day);
		}
	}
}
