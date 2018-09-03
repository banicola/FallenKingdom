package com.fk.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class TeamMenu extends JavaPlugin{
	private static Inventory teamMenu = Bukkit.createInventory(null, 9, "Choose a team");
	
	public static void teamMenuDisplay(Player p){
		
		ItemStack RED = new ItemStack(Material.RED_WOOL, 1);
		ItemMeta meta1 = (ItemMeta) RED.getItemMeta();
		meta1.setDisplayName("RED team");
		RED.setItemMeta(meta1);
		
		ItemStack BLUE = new ItemStack(Material.BLUE_WOOL, 1);
		ItemMeta meta2 = (ItemMeta) BLUE.getItemMeta();
		meta2.setDisplayName("BLUE team");
		BLUE.setItemMeta(meta2);
		
		ItemStack GREEN = new ItemStack(Material.GREEN_WOOL, 1);
		ItemMeta meta3 = (ItemMeta) GREEN.getItemMeta();
		meta3.setDisplayName("GREEN team");
		GREEN.setItemMeta(meta3);
		
		ItemStack YELLOW = new ItemStack(Material.YELLOW_WOOL, 1);
		ItemMeta meta4 = (ItemMeta) YELLOW.getItemMeta();
		meta4.setDisplayName("YELLOW team");
		YELLOW.setItemMeta(meta4);
		
		teamMenu.setItem(0, RED);
		teamMenu.setItem(1, BLUE);
		teamMenu.setItem(2, GREEN);
		teamMenu.setItem(3, YELLOW);
		p.openInventory(teamMenu);
	}
	
	public static void chooseTeamPlayer(Player p, String team){
		team = team.toUpperCase();
		ItemStack joinTeam = p.getInventory().getItem(4);
		if(Main.playersTeam.get(team).contains(p)) {
			p.sendMessage(ChatColor.RED+"You are already in the "+ChatColor.WHITE+team+ChatColor.RED+" team.");
		} else if(Main.playersTeam.get(team).size() == Main.config.getInt("max_team")) {
			p.sendMessage(ChatColor.RED+"The "+ChatColor.WHITE+team+ChatColor.RED+" team is already full.");
		} else if(Main.playerStatus.get(p)){
			p.sendMessage(ChatColor.RED+"You cannot join the "+ChatColor.WHITE+team+ChatColor.RED+" team when you are ready.");
		} else {
			try {
				String pTeam = Main.getPlayerTeam(p);
				Main.playersTeam.get(pTeam).remove(p);
			} catch(Exception e) {
				
			}
			
			Main.playersTeam.get(team).add(p);
			if(team.equals("RED")) {
				joinTeam = new ItemStack(Material.RED_WOOL, 1);
			} else if(team.equals("BLUE")) {
				joinTeam = new ItemStack(Material.BLUE_WOOL, 1);
			} else if(team.equals("GREEN")) {
				joinTeam = new ItemStack(Material.GREEN_WOOL, 1);
			} else if(team.equals("YELLOW")) {
				joinTeam = new ItemStack(Material.YELLOW_WOOL, 1);
			}
			p.sendMessage(ChatColor.BLUE+"You joined the "+ChatColor.WHITE+team+ChatColor.BLUE+" team !");
		}
		
		ItemMeta meta1 = (ItemMeta) joinTeam.getItemMeta();
		meta1.setDisplayName("Choose a team");
		joinTeam.setItemMeta(meta1);
		
		Bukkit.getServer().getPlayer(p.getName()).getInventory().setItem(4, joinTeam);
		
		p.closeInventory();
		
		int teamReady = 0;
		for(String teamList : Main.teams) {
			if(Main.playersTeam.get(teamList).size()>=Main.config.getInt("min_team")) {
				teamReady++;
			}
		}
		if(teamReady>=2) {
			for(Player po : Bukkit.getServer().getOnlinePlayers()) {
				if(Main.getPlayerTeam(po) != null && po.getInventory().getItem(8)==null) {
					ItemStack ready = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
					ItemMeta meta2 = (ItemMeta) ready.getItemMeta();
					meta2.setDisplayName("Not Ready");
					ready.setItemMeta(meta2);
					
					Bukkit.getServer().getPlayer(po.getName()).getInventory().setItem(8, ready);
				}
			}
		} else {
			for(Player po : Bukkit.getServer().getOnlinePlayers()) {
				if(Main.getPlayerTeam(po) != null && po.getInventory().getItem(8)!=null) {					
					Bukkit.getServer().getPlayer(po.getName()).getInventory().removeItem(po.getInventory().getItem(8));
					Main.playerStatus.put(p, false);
				}
			}
		}
	}

}
