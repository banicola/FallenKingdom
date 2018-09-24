package com.fk.event;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.fk.main.DistancePlayerLocation;
import com.fk.main.Main;

public class PlayerBuildEvent implements Listener{
	@SuppressWarnings("unlikely-arg-type")
	@EventHandler
	public void BlockPlaceEvent(BlockPlaceEvent e) {
		if(DistancePlayerLocation.distancePlayerSpawn(e.getPlayer())<Main.config.getInt("spawn_protection")) {
			e.getPlayer().sendMessage(ChatColor.RED+"You are too close from the spawn, go forward !");
			e.setCancelled(true);
			
		} else if(DistancePlayerLocation.distancePlayerBase(e.getPlayer())>15) {
			if(!e.getBlock().equals(Material.TNT) || !e.getBlock().equals(Material.TORCH) || !e.getBlock().equals(Material.REDSTONE_TORCH)) {
				e.getPlayer().sendMessage(ChatColor.RED+"You cannot build outside of your base !");
				e.setCancelled(true);
			}
		} else if(DistancePlayerLocation.inEnemyBase(e.getPlayer())) {
			if(!e.getBlock().equals(Material.TNT) && Main.day<=7) {
				e.getPlayer().sendMessage(ChatColor.RED+"You cannot build in the enemy base !");
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void BlockBreakEvent(BlockBreakEvent e) {
		if(DistancePlayerLocation.distancePlayerSpawn(e.getPlayer())<Main.config.getInt("spawn_protection")) {
			e.getPlayer().sendMessage(ChatColor.RED+"You are too close from the spawn, go forward !");
			e.setCancelled(true);
		} else if(DistancePlayerLocation.inEnemyBase(e.getPlayer())) {
			e.getPlayer().sendMessage(ChatColor.RED+"You cannot break block in the enemy base !");
			e.setCancelled(true);
		}
	}
}
