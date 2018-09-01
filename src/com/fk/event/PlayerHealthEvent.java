package com.fk.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import com.fk.main.Main;

public class PlayerHealthEvent implements Listener{
	@EventHandler
	public void PlayerDamageEvent(EntityDamageEvent e){
		if(!Main.gameStatus){
			e.setCancelled(true);
		}
	}

}
