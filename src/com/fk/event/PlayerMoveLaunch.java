package com.fk.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.fk.main.Main;

public class PlayerMoveLaunch implements Listener{
	@EventHandler
	public void playerMoveEvent(PlayerMoveEvent e) {
		
		if((Main.gameStatus && Main.day == 0)||Main.pause){
			e.setCancelled(true);
		}
	}
}

