package com.fk.main;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

public class CreateGameConfig extends JavaPlugin{
	
	public static boolean createSpawnLobby(String world, String type, int x, int y, int z){
		Main.config.set(type+".x", x);
		Main.config.set(type+".y", y);
		Main.config.set(type+".z", z);
		
		try {
			Main.config.save(Main.configFile);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
