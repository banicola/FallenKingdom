package com.fk.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.fk.main.CreateGameConfig;
import com.fk.main.Main;

public class FkCommand implements CommandExecutor, TabCompleter{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(Main.gameSetup) {
			sender.sendMessage(ChatColor.LIGHT_PURPLE+"Your server is setup and ready to play !\nDelete "+ChatColor.WHITE+"config.yml"+ChatColor.LIGHT_PURPLE+" and reload the server to start a new setup !");
			return true;
		}
		if(sender.isOp()) {
			
			Player p = (Player) sender;
			Location l = p.getLocation();
			int x = l.getBlockX(), y= l.getBlockY(), z = l.getBlockZ();
			
			if(args.length > 0) {
				
				if(args[0].equalsIgnoreCase("start") && Main.spawnStatus && Main.teamStatus) {
					if(Main.gameSetup){
						sender.sendMessage(ChatColor.RED+"This game has already been launched !\nYou can now start your own FallenKingdom !");
						return true;
					}
					Main.gameSetup = true;
					sender.sendMessage(ChatColor.GREEN+"Your server is now setup !\nYou can start to play when you want !\nGood luck.");
				} 
				
				else if(args[0].equalsIgnoreCase("setspawn")){
					if(args.length == 1){
						if(Main.config.getInt("spawn.y") == -1 || !(Main.config.getInt("spawn.x") == x && Main.config.getInt("spawn.y") == y && Main.config.getInt("spawn.z") == z)) {
							if(CreateGameConfig.createSpawn(l.getWorld().getName(), x, y, z)){
								sender.sendMessage(ChatColor.GREEN+"The spawn has been set to "+ChatColor.LIGHT_PURPLE+Main.config.getInt("spawn.x")+" "+Main.config.getInt("spawn.y")+" "+Main.config.getInt("spawn.z"));
							} 
						} else {
							sender.sendMessage(ChatColor.RED+"The spawn is already set to "+ChatColor.LIGHT_PURPLE+Main.config.getInt("spawn.x")+" "+Main.config.getInt("spawn.y")+" "+Main.config.getInt("spawn.z"));
							sender.sendMessage(ChatColor.RED+"You can still modify it by using: /fk create spawn <X> <Y> <Z>");
						}							
					} else if(args.length ==  4){
						
						for(int i=1;i<4;i++) {
							try{
						        int temp = Integer.parseInt(args[i]);
						        if(i==2 && (temp>256 || temp<0)){
						        	sender.sendMessage(ChatColor.RED+"Error: Y isn't in the range 0-256 !");
							        return true;
						        }
						        switch(i) {
							        case 1: x = temp;
							        		break;
							        case 2: y = temp;
							        		break;
							        case 3: z = temp;
							        		break;
						        }
						        	
						    }catch(Exception e){
						    	sender.sendMessage(ChatColor.RED+"Usage: /fk create spawn <X> <Y> <Z>");
						        return true;
						    }
						}
						if(Main.config.getInt("spawn.x")==x && Main.config.getInt("spawn.y")==y &&Main.config.getInt("spawn.z")==z){
							sender.sendMessage(ChatColor.RED+"The spawn is already set to "+ChatColor.LIGHT_PURPLE+Main.config.getInt("spawn.x")+" "+Main.config.getInt("spawn.y")+" "+Main.config.getInt("spawn.z"));
							return true;
						}
						CreateGameConfig.createSpawn(l.getWorld().getName(), x, y, z);
						sender.sendMessage(ChatColor.GREEN+"The spawn has been set to "+ChatColor.LIGHT_PURPLE+Main.config.getInt("spawn.x")+" "+Main.config.getInt("spawn.y")+" "+Main.config.getInt("spawn.z"));
						
					} else {
						sender.sendMessage(ChatColor.RED+"Usage: /fk create spawn <X> <Y> <Z>");
						return true;
					}
				} 
				
				else if(args[0].equalsIgnoreCase("team")){
					if(args.length == 1 || args.length == 2 || args.length>6) {
						sender.sendMessage(ChatColor.RED+"Usage: /fk team spawn <Name Team> <X> <Y> <Z>");
					} else if(args.length >= 3) {
						args[2] = args[2].toUpperCase();
						if(!Main.config.getString("team."+args[2]).isEmpty()){
							if(Main.config.getInt("team."+args[2]+".base.x")!=x || Main.config.getInt("team."+args[2]+".base.y")!=y || Main.config.getInt("team."+args[2]+".base.z")!=z){
								if(args.length == 6) {
									for(int i=3;i<6;i++) {
										try{
									        int temp = Integer.parseInt(args[i]);
									        if(i==4 && (temp>256 || temp<0)){
									        	sender.sendMessage(ChatColor.RED+"Error: Y isn't in the range 0-256 !");
										        return true;
									        }
									        switch(i) {
										        case 3: x = temp;
										        		break;
										        case 4: y = temp;
										        		break;
										        case 5: z = temp;
										        		break;
									        }
									        	
									    } catch(Exception e){
									    	sender.sendMessage(ChatColor.RED+"Usage: /fk team spawn <Name Team> <X> <Y> <Z>");
									        return true;
									    }
									}
								}
								CreateGameConfig.createTeamSpawn(args[2], x, y, z);
								sender.sendMessage(ChatColor.GREEN+"The "+args[2]+" spawn been set to "+ChatColor.LIGHT_PURPLE+Main.config.getInt("team."+args[2]+".base.x")+" "+Main.config.getInt("team."+args[2]+".base.y")+" "+Main.config.getInt("team."+args[2]+".base.z"));
							}
						} else {
							sender.sendMessage(ChatColor.RED+"Error: The team "+ChatColor.WHITE+args[2]+ChatColor.RED+" doesn't exists !");
					        return true;
						}						
					}
				}
				
			} else {
				//Default message
				sender.sendMessage(ChatColor.BLUE + "=========== Fallen Kingdom ============");
				String spawnStatusColor= ChatColor.RED+""+Main.spawnStatus;
				String teamStatusColor = ChatColor.RED+""+Main.teamStatus;
				if(Main.config.getInt("spawn.y")!=-1) {
					Main.spawnStatus = true;
					spawnStatusColor = ChatColor.GREEN+""+Main.spawnStatus;
				}
				if(Main.config.getInt("team.RED.base.y")!=-1 && Main.config.getInt("team.BLUE.base.y")!=-1 && Main.config.getInt("team.GREEN.base.y")!=-1 && Main.config.getInt("team.PURPLE.base.y")!=-1){
					Main.teamStatus = true;
					teamStatusColor = ChatColor.GREEN+""+Main.teamStatus;
				}
				if(Main.spawnStatus && Main.teamStatus) {
					sender.sendMessage(ChatColor.GREEN + "/fk start : start game\n");
				}
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "/fk setspawn : Setup the spawn ("+spawnStatusColor+ChatColor.LIGHT_PURPLE+")\n/fk team : Change team settings ("+teamStatusColor+ChatColor.LIGHT_PURPLE+")\n");
				sender.sendMessage(ChatColor.BLUE + "====================================");
			}
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("fk") || cmd.getName().equalsIgnoreCase("fallenkingdom")) {
			ArrayList<String> commandList = new ArrayList<String>();
			if(args.length == 1){
				commandList.add("start");
				commandList.add("setspawn");
				commandList.add("team");
				ArrayList<String> commandFound = new ArrayList<String>();
				for(String s: commandList) {
					if(s.toLowerCase().startsWith(args[0].toLowerCase())){
						commandFound.add(s);
					}
				}
				return commandFound;
				
			} else if(args.length == 2){
				if(args[0].equalsIgnoreCase("team")) {
					commandList.add("spawn");
					ArrayList<String> commandFound = new ArrayList<String>();
					for(String s: commandList) {
						if(s.toLowerCase().startsWith(args[1].toLowerCase())){
							commandFound.add(s);
						}
					}
					return commandFound;
				}
			} else if(args.length == 3){
				if(args[0].equalsIgnoreCase("team") && (args[1].equalsIgnoreCase("spawn"))){
					commandList.add("RED");
					commandList.add("BLUE");
					commandList.add("GREEN");
					commandList.add("PURPLE");
					ArrayList<String> commandFound = new ArrayList<String>();
					for(String s: commandList) {
						if(s.toLowerCase().startsWith(args[2].toLowerCase())){
							commandFound.add(s);
						}
					}
					return commandFound;
				}
			}
		}
		return new ArrayList<String>();
	}

}
