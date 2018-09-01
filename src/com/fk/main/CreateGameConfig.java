package com.fk.main;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

public class CreateGameConfig extends JavaPlugin{
	
	public static boolean createConfig(int x, int y, int z){
		Main.config.set("spawn.x", x);
		Main.config.set("spawn.y", y);
		Main.config.set("spawn.z", z);
		
		try {
			Main.config.save(Main.configFile);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
