package com.fk.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.fk.main.Main;

import net.md_5.bungee.api.ChatColor;

public class PlayerChatEvent implements Listener{
	
	@EventHandler
	public void asyncPlayerChatEvent(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		String team = Main.getPlayerTeam(p);
		ChatColor color = ChatColor.WHITE;
		if(team != null){
			if(team == "RED") {color = ChatColor.RED;}
			else if(team == "BLUE") {color = ChatColor.BLUE;}
			else if(team == "GREEN") {color = ChatColor.GREEN;}
			else if(team == "YELLOW") {color = ChatColor.YELLOW;}
		}
		e.setFormat("<" + color + p.getDisplayName() + ChatColor.WHITE + "> " + e.getMessage());
	}
}