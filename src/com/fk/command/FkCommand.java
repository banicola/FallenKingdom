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
		if(sender.isOp()) {
			if(args.length > 0) {
				
				if(args[0].equalsIgnoreCase("start")) {
					sender.sendMessage(ChatColor.RED+ args[0]+" command unavailable!");
					return true;
					
					/*if(!Main.config.getString("team").isEmpty()){
						Main.gameSetup = true;
					}*/
				} 
				
				else if(args[0].equalsIgnoreCase("setspawn")){
					Player p = (Player) sender;
					Location l = p.getLocation();
					int x = l.getBlockX(), y= l.getBlockY(), z = l.getBlockZ();
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
					sender.sendMessage(ChatColor.RED+ args[0]+" command unavailable!");
				}
				
			} else {
				//Default message
				sender.sendMessage(ChatColor.BLUE + "=========== Fallen Kingdom ===========");
				sender.sendMessage(ChatColor.GREEN + "/fk start : start  game\n");
				if (sender.isOp()){
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "/fk setspawn : Setup the spawn\n/fk team : Change team settings\n");
				}
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
				//Not use yet
			}
		}
		return new ArrayList<String>();
	}

}
