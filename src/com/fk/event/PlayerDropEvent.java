package com.fk.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.fk.main.Main;

public class PlayerDropEvent implements Listener{
	
	@EventHandler
	public void PlayerDropItemEvent(PlayerDropItemEvent e) {
		if(!Main.gameStatus) {
			e.setCancelled(true);
		}
	}

}
