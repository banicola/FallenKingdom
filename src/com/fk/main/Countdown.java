package com.fk.main;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.fk.event.NewDayEvent;

public class Countdown extends JavaPlugin{
	
	static int time;
	static int TaskID;
	String game;
	
	public static void CountdownStart(int amount, boolean started) {
        time = amount;
        
        TaskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Bukkit.getServer().getPluginManager().getPlugin("FallenKingdom"), new Runnable() {
            @SuppressWarnings("unused")
			@Override
            public void run() {
            	if(started) {
            		time++;
            		if(time%1200==0) {
            			NewDayEvent.dayChangeEvent();
            		}
            		if(time==60) {
            			Bukkit.getServer().broadcastMessage(ChatColor.LIGHT_PURPLE+String.format("[%s] Did you know? You can ask for a pause with: \n%s", ChatColor.WHITE+"FallenKingdom"+ChatColor.LIGHT_PURPLE, ChatColor.WHITE+"/fk pause"));
            		}
            	} else {
            		if(time >= 0){
                		if(!Main.pause||Main.votePause==0) {
                			if(time == 0 && !Main.gameStatus) {
                    			for(String t : Main.teams) {
                    				if(Main.playersTeam.get(t).size()!=0) {
                    					int idx = new Random().nextInt(Main.playersTeam.get(t).size());
                        				Main.teamLeader.put(t, Main.playersTeam.get(t).get(idx));
                    				}
                    			}
                    		}
                			for (Player p : Bukkit.getServer().getOnlinePlayers()){
                    			if(Main.getPlayerTeam(p) == null && Main.gameStatus) {
                    				p.kickPlayer("Sorry, you were not in a team when the game launched !");
                    			}
                				p.setLevel(time);
                				
                				if(time <= 5 && !Main.gameStatus){
                					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10, 1);
                				}
                				if(time == 0){
                					if(Main.gameStatus) {
                						p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 10, 1);
                					}
                				}
                			}
            				if(time == 0){
            					if(Main.gameStatus) {
            						Bukkit.getServer().getWorld(Main.config.getString("world")).setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
            						Main.day=1;
            						Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE+"Day "+ChatColor.GREEN+Main.day);
            						Bukkit.getScheduler().cancelTask(TaskID);
            						StartGame.inGame();
            					} else {
            						Main.gameStatus = true;
            						StartGame.PlayerSetup();
            						Bukkit.getScheduler().cancelTask(TaskID);
            						StartGame.LauchGame();
            					}
            				}
                		} else if(false) {
                			for (Player p : Bukkit.getServer().getOnlinePlayers()){
                				if(time <= 5){
                					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10, 1);
                				}
                			}
                			if(!Main.pause) {
                				if(Main.votePause>=Bukkit.getServer().getOnlinePlayers().size()/2) {
                    				time=0;
                    				Main.pause = true;
                    				Main.votePause=0;
                    				Bukkit.getServer().getWorld(Main.config.getString("world")).setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                    			}
                				if(time == 15||(time<=5 && time>0)) {
                    				Bukkit.broadcastMessage(ChatColor.GREEN+"You have "+ChatColor.WHITE+time+ChatColor.GREEN+" seconds to vote.");
                    			} else if(time == 0) {
                    				String s = "against";
                    				if(Main.pause) {
                    					s = "for";
                    				}
                    				Bukkit.broadcastMessage(ChatColor.GREEN+"You voted "+ChatColor.WHITE+s+ChatColor.GREEN+" a pause.");
                    				Bukkit.getScheduler().cancelTask(TaskID);
                    				if(Main.pause) {
                    					Pause.PauseCountdown();
                    				}
                    			}
                			} else {
                				if(time==30) {
                					Bukkit.broadcastMessage(ChatColor.GREEN+"You still have "+ChatColor.WHITE+time+ChatColor.GREEN+" seconds of pause.");
                				} else if(time==10) {
                					Bukkit.broadcastMessage(ChatColor.GREEN+"The pause end in "+ChatColor.WHITE+time);
                				} else if(time<10 && time>0) {
                					Bukkit.broadcastMessage(ChatColor.WHITE+""+time);
                				} else if(time==0) {
                					Main.pause=false;
                					Bukkit.getServer().getWorld(Main.config.getString("world")).setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
                					for (Player p : Bukkit.getServer().getOnlinePlayers()){
                						Main.vote.put(p, false);
                        			}
                					Bukkit.getScheduler().cancelTask(TaskID);
                				}
                			}
                			
                		}
            			time--;
                	}
            	}
            }
        }, 0L, 20L);
    }
}
