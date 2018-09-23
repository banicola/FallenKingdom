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
}
