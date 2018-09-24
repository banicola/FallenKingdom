package com.fk.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class DistancePlayerLocation extends JavaPlugin{
	public static Double distancePlayerSpawn(Player p) {
		Location loc = p.getLocation();
		Location spawn = new Location(Bukkit.getWorld(Main.config.getString("world")), Main.config.getInt("spawn.x"), Main.config.getInt("spawn.y"), Main.config.getInt("spawn.z"));
		return loc.distance(spawn);
	}

	public static Double distancePlayerBase(Player p) {
		Location loc = p.getLocation();
		try {
			Location base = Main.teamBase.get(Main.getPlayerTeam(p));
			return loc.distance(base);
		} catch (Exception e) {
			
		}
		
		return (double) -1;
	}

	public static boolean inEnemyBase(Player p) {
		Location loc = p.getLocation();
		for(String team : Main.teams) {
			if(!team.equals(Main.getPlayerTeam(p))) {
				try {
					Location enemy = Main.teamBase.get(team);
					if(loc.distance(enemy)<=15) {
						return true;
					}
				} catch (Exception e) {
					
				}				
			}
		}
		return false;
	}
}
