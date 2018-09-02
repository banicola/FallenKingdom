package com.fk.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.fk.main.Main;

public class PlayerJoinedEvent implements Listener{
	
	@EventHandler
	public void PlayerJoinEvent(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		Bukkit.getServer().getPlayer(p.getName()).getInventory().clear();
		p.setHealth(20);
		p.setFoodLevel(20);
		p.setExp(0);
		
		if(!Main.gameStatus && Main.gameSetup) {
			
			p.setGameMode(GameMode.ADVENTURE);
			
			ItemStack joinTeam = new ItemStack(Material.WHITE_WOOL, 1);
			ItemMeta meta1 = (ItemMeta) joinTeam.getItemMeta();
			meta1.setDisplayName("Choose a team");
			joinTeam.setItemMeta(meta1);
			
			Bukkit.getServer().getPlayer(p.getName()).getInventory().setItem(4, joinTeam);
		}
		if(!Main.gameSetup && p.isOp()){
			e.setJoinMessage("");
			e.getPlayer().sendMessage(ChatColor.BLUE+"***************************************************************************"+ChatColor.GREEN+"\nWelcome on the server!\n");
			e.getPlayer().sendMessage(ChatColor.GREEN+"\nYour server isn't ready to start a Fallen Kingdom game yet.\n");
			e.getPlayer().sendMessage(ChatColor.GREEN+"\nPlease use "+ChatColor.WHITE+"/fk"+ChatColor.GREEN+" to learn how to setup your server!\n"+ChatColor.BLUE+"***************************************************************************");
			
		}
		return;
	}
}
