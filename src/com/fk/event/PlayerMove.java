package com.fk.event;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.fk.main.DistancePlayerLocation;
import com.fk.main.Main;

public class PlayerMove implements Listener{
	@EventHandler
	public void playerMoveEvent(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		
		if((Main.gameStatus && Main.day == 0)||Main.pause){
			e.setCancelled(true);
		} else if(Main.gameStatus && Main.day >= 1) {
			try {
				int dist = DistancePlayerLocation.distancePlayerBase(p).intValue();
				if(dist>=15 && dist<=16) {
					if(Main.playerInBase.get(p)&&dist==16) {
						p.sendMessage(ChatColor.GOLD+"You left your base !");
						Main.playerInBase.put(p, false);
					} else if(!Main.playerInBase.get(p)&&dist==15) {
						p.sendMessage(ChatColor.GOLD+"You entered your base !");
						Main.playerInBase.put(p, true);
					}
				}
			} catch (Exception exception){
				
			}
		}
	}
}

