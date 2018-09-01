package com.fk.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import com.fk.main.Main;

public class EntityEvent implements Listener{
	@EventHandler
	public void EntityDropItemEvent(EntityDropItemEvent e){
		if(!Main.gameStatus){
			e.setCancelled(true);
		}
	}
}
