package com.fk.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinedEvent implements Listener{
	
	@EventHandler
	public void PlayerJoinEvent(PlayerJoinEvent e) {
		e.getPlayer().setHealth(20);
		e.getPlayer().setFoodLevel(20);
		return;
	}
}
