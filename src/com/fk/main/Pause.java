package com.fk.main;

import org.bukkit.plugin.java.JavaPlugin;

public class Pause extends JavaPlugin{
	public static void PauseCountdown(){
		Countdown.CountdownStart(60, false);
	}
	
	public static void PauseVoteCountdown(){
		Countdown.CountdownStart(15, false);
	}
}
