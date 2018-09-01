package com.fk.main;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

public class CreateGameConfig extends JavaPlugin{
	
	public static boolean createSpawn(int x, int y, int z){
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
	
	public static boolean createTeam(String name, int size){
		Main.config.set("team", name);
		Main.config.set("team."+name+".size", size);
		
		try {
			Main.config.save(Main.configFile);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean createTeamSpawn(String name, int x, int y, int z){
		if(Main.config.getString("team").contentEquals(name)){
			Main.config.set("team."+name+"base.x", x);
			Main.config.set("team."+name+"base.y", y);
			Main.config.set("team."+name+"base.z", z);
		} else {
			return false;
		}
		
		try {
			Main.config.save(Main.configFile);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
