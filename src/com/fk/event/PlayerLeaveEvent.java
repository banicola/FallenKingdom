package com.fk.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.fk.main.Main;

public class PlayerLeaveEvent implements Listener{
	@EventHandler
	public void PlayerQuitEvent(PlayerQuitEvent e) {
		if(!Main.gameStatus) {
			try {
				Player p = e.getPlayer();
				Main.playersTeam.get(Main.getPlayerTeam(p)).remove(p);
				Main.playerStatus.remove(p);
			} catch(Exception exception) {
				
			}
		}
	}
}
