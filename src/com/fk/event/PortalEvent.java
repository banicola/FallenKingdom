package com.fk.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;

public class PortalEvent implements Listener{
	
	@EventHandler
	public void PortalCreateEvent(PortalCreateEvent e) {
		e.setCancelled(true);
	}
}
