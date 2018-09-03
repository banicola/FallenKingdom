package com.fk.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.fk.command.FkCommand;
import com.fk.event.EntityEvent;
import com.fk.event.PlayerDropEvent;
import com.fk.event.PlayerFoodEvent;
import com.fk.event.PlayerHealthEvent;
import com.fk.event.PlayerInventoryClick;
import com.fk.event.PlayerJoinedEvent;
import com.fk.event.PlayerLeaveEvent;
import com.fk.event.PlayerMoveLaunch;
import com.fk.event.PlayerNewLoginEvent;

public class Main extends JavaPlugin{
	
	public static HashMap<String, List<Player>> playersTeam = new HashMap<String, List<Player>>();
	
	public static boolean gameSetup = false;
	public static boolean gameStatus = false;
	public static boolean spawnStatus = false;
	public static boolean lobbyStatus = false;
	
	public static int day = 0;
	
	public static File configFile;
    public static FileConfiguration config;

	public static void main(String[] args) {}
	
	public void onEnable(){
		
		createFiles();
		
		PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerNewLoginEvent(), this);
        pm.registerEvents(new PlayerJoinedEvent(), this);
        pm.registerEvents(new PlayerFoodEvent(), this);
        pm.registerEvents(new PlayerHealthEvent(), this);
        pm.registerEvents(new PlayerInventoryClick(), this);
        pm.registerEvents(new PlayerLeaveEvent(), this);
        pm.registerEvents(new PlayerDropEvent(), this);
        pm.registerEvents(new PlayerMoveLaunch(), this);
        pm.registerEvents(new EntityEvent(), this);
        
		CommandExecutor fallenkingdomExecutor = new FkCommand();
    	getCommand("fallenkingdom").setExecutor(fallenkingdomExecutor);
    	
    	playersTeam.put("RED", new ArrayList<Player>());
    	playersTeam.put("BLUE", new ArrayList<Player>());
    	playersTeam.put("GREEN", new ArrayList<Player>());
    	playersTeam.put("YELLOW", new ArrayList<Player>());
    	
    	if(config.getInt("spawn.y")!=-1 && config.getInt("lobby.y")!=-1){
    		spawnStatus = true;
    		lobbyStatus = true;
    		gameSetup = true;
    	}
	}

	public void onDisable(){
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			p.kickPlayer("The plugin FallenKingdom has been reloaded !");
		}
	}
	
	private void createFiles() {
		configFile = new File(getDataFolder(), "config.yml");

        if (!configFile.exists()) {
        	configFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }
        loadFiles();
    }
	
	public static void loadFiles(){
		config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public static String getPlayerTeam(Player player){
		if(Main.playersTeam.get("RED").contains(player)){
			return "RED";
		} else if(Main.playersTeam.get("BLUE").contains(player)){
			return "BLUE";
		} else if(Main.playersTeam.get("GREEN").contains(player)){
			return "GREEN";
		} else if(Main.playersTeam.get("YELLOW").contains(player)){
			return "YELLOW";
		}
		return null;
	}

}
