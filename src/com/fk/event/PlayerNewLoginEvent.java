package com.fk.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import com.fk.main.Main;

public class PlayerNewLoginEvent implements Listener{
	
	@EventHandler
	public void PlayerPreLoginEvent(AsyncPlayerPreLoginEvent e) {
		if(Main.gameStatus){
			e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "Game already started !");
		}
		return;
	}

}
