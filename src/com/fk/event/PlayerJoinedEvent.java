package com.fk.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.fk.main.Main;

public class PlayerJoinedEvent implements Listener{
	
	@EventHandler
	public void PlayerJoinEvent(PlayerJoinEvent e) {
		if(!Main.gameStatus) {
			e.getPlayer().setHealth(20);
			e.getPlayer().setFoodLevel(20);
			e.getPlayer().setExp(0);
		}
		return;
	}
}
