package com.fk.main;

import java.io.File;
import java.io.IOException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.fk.command.FkCommand;
import com.fk.event.PlayerFoodEvent;
import com.fk.event.PlayerHealthEvent;
import com.fk.event.PlayerJoinedEvent;
import com.fk.event.PlayerNewLoginEvent;

public class Main extends JavaPlugin{
	
	
	
	public static boolean gameSetup = false;
	public static boolean gameStatus = false;
	
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
		
		CommandExecutor fallenkingdomExecutor = new FkCommand();
    	getCommand("fallenkingdom").setExecutor(fallenkingdomExecutor);
	}

	public void onDisable(){}
	
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

}
