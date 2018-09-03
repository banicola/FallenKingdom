package com.fk.event;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.fk.main.Main;
import com.fk.main.TeamMenu;

public class PlayerInventoryClick implements Listener{
	@EventHandler
	public void playerInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(!Main.gameStatus){
			e.setCancelled(true);
			if(e.getCurrentItem() != null && !e.getCurrentItem().getType().equals(Material.AIR)){
				if(e.getCurrentItem().getItemMeta().getDisplayName().equals("Choose a team")){
					TeamMenu.teamMenuDisplay(p);  	  		  		
		  		} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("RED team")){
	  		  		TeamMenu.chooseTeamPlayer(p, "RED");
	  		  	} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("BLUE team")){
	  		  		TeamMenu.chooseTeamPlayer(p, "BLUE");
	  		  	} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("GREEN team")){
	  		  		TeamMenu.chooseTeamPlayer(p, "GREEN");
	  		  	} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("YELLOW team")){
	  		  		TeamMenu.chooseTeamPlayer(p, "YELLOW");
	  		  	}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		if(!Main.gameStatus){
			Player p = (Player) e.getPlayer();
			e.setCancelled(true);
			if(p.getInventory().getHeldItemSlot() == 4){
				TeamMenu.teamMenuDisplay(p);
			} else if(p.getInventory().getHeldItemSlot() == 8){
				if(p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("Not Ready")) {
					ItemStack ready = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
					ItemMeta meta2 = (ItemMeta) ready.getItemMeta();
					meta2.setDisplayName("Ready");
					ready.setItemMeta(meta2);
					
					Bukkit.getServer().getPlayer(p.getName()).getInventory().setItem(8, ready);
					Main.playerStatus.put(p, true);
					
				} else if(p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("Ready")) {
					ItemStack ready = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
					ItemMeta meta2 = (ItemMeta) ready.getItemMeta();
					meta2.setDisplayName("Not Ready");
					ready.setItemMeta(meta2);
					
					Bukkit.getServer().getPlayer(p.getName()).getInventory().setItem(8, ready);
					Main.playerStatus.put(p, false);
				}
			}
		}
	}
}
