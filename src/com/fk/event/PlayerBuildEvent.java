package com.fk.event;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import com.fk.main.Main;

public class PlayerBuildEvent implements Listener{
	@EventHandler
	public void BlockPlaceEvent(BlockPlaceEvent e) {
		for(String team : Main.teams) {
			if(!team.equalsIgnoreCase(Main.getPlayerTeam(e.getPlayer()))){
				if((e.getBlockPlaced().getLocation().getBlockX()>=Main.teamBase.get(team).getBlockX()-15 && e.getBlockPlaced().getLocation().getBlockX()<=Main.teamBase.get(team).getBlockX()+15) && (e.getBlockPlaced().getLocation().getBlockZ()>=Main.teamBase.get(team).getBlockZ()-15) && e.getBlockPlaced().getLocation().getBlockZ()<=Main.teamBase.get(team).getBlockX()+15) {
					if(Main.day>=7 && e.getBlock().getBlockData().getMaterial().equals(Material.TNT)) {
						return;
					}
					e.setBuild(false);
				}
			}
		}
		if(!e.canBuild()) {
			e.setCancelled(true);
		}
	}
}
