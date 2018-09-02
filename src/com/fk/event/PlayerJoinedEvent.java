package com.fk.event;

import org.bukkit.ChatColor;
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
		if(!Main.gameSetup){
			e.setJoinMessage("");
			e.getPlayer().sendMessage(ChatColor.BLUE+"***************************************************************************"+ChatColor.GREEN+"\nWelcome on the server!\n");
			e.getPlayer().sendMessage(ChatColor.GREEN+"\nYour server isn't ready to start a Fallen Kingdom game yet.\n");
			e.getPlayer().sendMessage(ChatColor.GREEN+"\nPlease use "+ChatColor.WHITE+"/fk"+ChatColor.GREEN+" to learn how to setup your server!\n"+ChatColor.BLUE+"***************************************************************************");
			
		}
		return;
	}
}
