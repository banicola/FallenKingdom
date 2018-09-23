package com.fk.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.fk.main.Main;
import com.fk.main.TeamReadyCheck;

import net.md_5.bungee.api.ChatColor;

public class PlayerLeaveEvent implements Listener{
	@EventHandler
	public void PlayerQuitEvent(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
		if(!Main.gameStatus) {
			try {
				
				Main.playerStatus.put(p, false);
				TeamReadyCheck.isTeamReady(p);
				Main.playersTeam.get(Main.getPlayerTeam(p)).remove(p);
				Main.playerStatus.remove(p);
			} catch(Exception exception) {
				
			}
		} else {
			e.setQuitMessage(ChatColor.YELLOW+p.getName()+" left the game. (Team "+ChatColor.WHITE+Main.getPlayerTeam(p)+ChatColor.YELLOW+")");
		}
	}
}
