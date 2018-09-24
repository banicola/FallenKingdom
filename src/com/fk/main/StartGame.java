package com.fk.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class StartGame extends JavaPlugin{
	
	public static void LobbyCountdown(){
		Countdown.CountdownStart(15, false);
	}
	public static void LauchGame(){
		Countdown.CountdownStart(5, false);
	}
	public static void inGame() {
		Countdown.CountdownStart(1, true);
	}
	public static void PlayerSetup() {
		Location spawn = new Location(Bukkit.getServer().getWorld(Main.config.getString("world")), Main.config.getInt("spawn.x"), Main.config.getInt("spawn.y"), Main.config.getInt("spawn.z"));
		for (Player p : Bukkit.getServer().getOnlinePlayers()){
			p.teleport(spawn);
			p.setGameMode(GameMode.SURVIVAL);
			p.getInventory().clear();
			Main.vote.put(p, false);
			Main.playerInBase.put(p, false);
			p.sendMessage(ChatColor.BLUE+"The "+ChatColor.WHITE+Main.getPlayerTeam(p)+ChatColor.BLUE+" team leader is "+ChatColor.WHITE+Main.teamLeader.get(Main.getPlayerTeam(p)).getName()+ChatColor.BLUE+" !");
		}		
		Bukkit.getServer().broadcastMessage(ChatColor.GOLD+String.format("[%s] Each team leader has to setup is base with: \n%s", ChatColor.WHITE+"FallenKingdom"+ChatColor.GOLD, ChatColor.WHITE+"/fk setbase"));
	}

}
