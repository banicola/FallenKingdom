package com.fk.main;

import java.util.ArrayList;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.fk.command.FkCommand;
import com.fk.event.PlayerFoodEvent;
import com.fk.event.PlayerHealthEvent;
import com.fk.event.PlayerJoinedEvent;
import com.fk.event.PlayerNewLoginEvent;

public class Main extends JavaPlugin{
	
	public static ArrayList<String> commandList = new ArrayList<String>();
	public static boolean gameSetup = false;
	public static boolean gameStatus = false;

	public static void main(String[] args) {}
	
	public void onEnable(){
		
		PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerNewLoginEvent(), this);
        pm.registerEvents(new PlayerJoinedEvent(), this);
        pm.registerEvents(new PlayerFoodEvent(), this);
        pm.registerEvents(new PlayerHealthEvent(), this);
        
		commandList.add("start");
		commandList.add("create");
		commandList.add("remove");
		
		CommandExecutor fallenkingdomExecutor = new FkCommand();
    	getCommand("fallenkingdom").setExecutor(fallenkingdomExecutor);
	}
	
	public void onDisable(){
		
	}

}
