package com.fk.main;

import org.bukkit.plugin.java.JavaPlugin;

public class StartGame extends JavaPlugin{
	
	public static void LobbyCountdown(){
		Countdown.CountdownStart(15);
	}
	public static void LauchGame(){
		Countdown.CountdownStart(5);
	}

}
