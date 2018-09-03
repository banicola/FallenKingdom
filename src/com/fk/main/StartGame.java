package com.fk.main;

import org.bukkit.plugin.java.JavaPlugin;

public class StartGame extends JavaPlugin{
	
	public static void startGame(){
		Countdown.CountdownStart(20);
	}
	public static void LauchGame(){
		Countdown.CountdownStart(5);
	}

}
