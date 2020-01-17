package com.fk.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.fk.command.FkCommand;
import com.fk.event.EntityEvent;
import com.fk.event.NewDayEvent;
import com.fk.event.PlayerBuildEvent;
import com.fk.event.PlayerChatEvent;
import com.fk.event.PlayerDropEvent;
import com.fk.event.PlayerFoodEvent;
import com.fk.event.PlayerHealthEvent;
import com.fk.event.PlayerInventoryClick;
import com.fk.event.PlayerJoinedEvent;
import com.fk.event.PlayerLeaveEvent;
import com.fk.event.PlayerMove;
import com.fk.event.PlayerNewLoginEvent;
import com.fk.event.PortalEvent;

public class Main extends JavaPlugin{
	
	private static Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
	
	public static HashMap<String, List<Player>> playersTeam = new HashMap<String, List<Player>>();
	public static HashMap<Player, Boolean> playerStatus = new HashMap<Player, Boolean>();
	
	public static ArrayList<String> teams = new ArrayList<String>();
	public static HashMap<String, Boolean> teamStatus = new HashMap<String, Boolean>();
	public static HashMap<String, Player> teamLeader = new HashMap<String, Player>();
	public static HashMap<String, Location> teamBase = new HashMap<String, Location>();
	
	public static HashMap<Player, Boolean> playerInBase = new HashMap<Player, Boolean>();
	
	public static HashMap<Player, Boolean> vote = new HashMap<Player, Boolean>();
	
	public static boolean gameSetup = false;
	public static boolean gameStatus = false;
	public static boolean spawnStatus = false;
	public static boolean lobbyStatus = false;
	public static boolean countdownStatus = false;
	public static boolean pause = false;
	
	public static int day = 0;
	public static int teamReady = 0;
	public static int votePause = 0;
	
	public static File configFile;
    public static FileConfiguration config;
    
    public static Team redteam;
    public static Team blueteam;
	public static Team greenteam;
	public static Team yellowteam;

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
        pm.registerEvents(new PlayerChatEvent(), this);
        pm.registerEvents(new PlayerMove(), this);
        pm.registerEvents(new PlayerBuildEvent(), this);
        pm.registerEvents(new NewDayEvent(), this);
        pm.registerEvents(new EntityEvent(), this);
        pm.registerEvents(new PortalEvent(), this);
        
		CommandExecutor fallenkingdomExecutor = new FkCommand();
    	getCommand("fallenkingdom").setExecutor(fallenkingdomExecutor);
    	
    	teams.add("RED");
    	teams.add("GREEN");
    	teams.add("BLUE");
    	teams.add("YELLOW");
    	
    	for(Team t : sb.getTeams()) {
    		t.unregister();
    	}
    	
    	redteam = sb.registerNewTeam("REDTEAM");
    	blueteam = sb.registerNewTeam("BLUETEAM");
    	greenteam = sb.registerNewTeam("GREENTEAM");
    	yellowteam = sb.registerNewTeam("YELLOWTEAM");
    	redteam.setColor(ChatColor.RED);
		blueteam.setColor(ChatColor.BLUE);
		greenteam.setColor(ChatColor.GREEN);
		yellowteam.setColor(ChatColor.YELLOW);
    	
    	for(String team : teams) {
    		playersTeam.put(team, new ArrayList<Player>());
    		teamStatus.put(team, false);
    	}
    	
    	if(config.getInt("spawn.y")!=-1 && config.getInt("lobby.y")!=-1){
    		spawnStatus = true;
    		lobbyStatus = true;
    		gameSetup = true;
    	}

    	setupWorld();
	}

	public void onDisable(){
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			p.kickPlayer("Server shutdown. Come back later !");
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
	
	private void setupWorld() {
		World world= Bukkit.getWorld(Main.config.getString("world"));
		world.setFullTime(0);
		world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
		world.setStorm(false);		
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
