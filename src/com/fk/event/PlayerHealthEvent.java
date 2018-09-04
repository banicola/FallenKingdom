package com.fk.event;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import com.fk.main.Main;

public class PlayerHealthEvent implements Listener{
	@EventHandler
	public void PlayerDamageEvent(EntityDamageEvent e){
		if((!Main.gameStatus || Main.day == 0) && e.getEntityType().equals(EntityType.PLAYER)){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void PlayerDamageByPlayerEvent(EntityDamageByEntityEvent e){
		if(Main.day < 3 && e.getEntityType().equals(EntityType.PLAYER)){
			if(e.getDamager() instanceof Player){
				e.setCancelled(true);
			}
		}
	}

}
