package com.fk.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import com.fk.main.Main;

public class PlayerFoodEvent implements Listener{
	
	@EventHandler
	public void foodLevelChange(FoodLevelChangeEvent e){
		if(!Main.gameStatus){
			e.setCancelled(true);
		}
	}

}
