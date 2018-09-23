package com.fk.event;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.fk.main.DistancePlayerLocation;
import com.fk.main.Main;

public class PlayerBuildEvent implements Listener{
	@EventHandler
	public void BlockPlaceEvent(BlockPlaceEvent e) {
		if(DistancePlayerLocation.distancePlayerSpawn(e.getPlayer())<Main.config.getInt("spawn_protection")) {
			e.getPlayer().sendMessage(ChatColor.RED+"You are too close from the spawn, go forward!");
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void BlockBreakEvent(BlockBreakEvent e) {
		if(DistancePlayerLocation.distancePlayerSpawn(e.getPlayer())<Main.config.getInt("spawn_protection")) {
			e.getPlayer().sendMessage(ChatColor.RED+"You are too close from the spawn, go forward!");
			e.setCancelled(true);
		}
	}
}
