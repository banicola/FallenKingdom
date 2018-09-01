package com.fk.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.fk.main.CreateGameConfig;
import com.fk.main.Main;

public class FkCommand implements CommandExecutor, TabCompleter{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.isOp()) {
			if(args.length > 0) {
				if(args[0].equalsIgnoreCase("start")||args[0].equalsIgnoreCase("remove")){
					sender.sendMessage(ChatColor.RED+ args[0]+" command unavailable!");
					return true;
				} else if(args[0].equalsIgnoreCase("create") && sender.isOp()){
					if(args.length > 1) {
						if(args.length == 2 && args[1].equalsIgnoreCase("spawn") && Main.config.getString("spawn").isEmpty()){
							if(CreateGameConfig.createConfig(0, 64, 0)){
								sender.sendMessage(ChatColor.GREEN+"The spawn has been set to "+ChatColor.LIGHT_PURPLE+Main.config.getInt("spawn.x")+" "+Main.config.getInt("spawn.y")+" "+Main.config.getInt("spawn.z"));
							} else {
								sender.sendMessage(ChatColor.RED+"The spawn was already set to "+ChatColor.LIGHT_PURPLE+Main.config.getInt("spawn.x")+" "+Main.config.getInt("spawn.y")+" "+Main.config.getInt("spawn.z"));
								sender.sendMessage(ChatColor.RED+"You can still modify it by using: /fk create spawn <X> <Y> <Z>");
							}
						} else if(args.length ==  5 && args[1].equalsIgnoreCase("spawn")){
							int x = 0, y= 60, z = 0;
							for(int i=2;i<5;i++) {
								try{
							        int temp = Integer.parseInt(args[i]);
							        switch(i) {
								        case 2: x = temp;
								        		break;
								        case 3: y = temp;
								        		break;
								        case 4: z = temp;
								        		break;
							        }
							        	
							    }catch(Exception e){
							    	sender.sendMessage(ChatColor.RED+"Usage: /fk create spawn <X> <Y> <Z>");
							        return true;
							    }
							}
							CreateGameConfig.createConfig(x, y, z);
							sender.sendMessage(ChatColor.GREEN+"The spawn has been set to "+ChatColor.LIGHT_PURPLE+Main.config.getInt("spawn.x")+" "+Main.config.getInt("spawn.y")+" "+Main.config.getInt("spawn.z"));
							
						} else {
							sender.sendMessage(ChatColor.RED+"You can still modify it by using: /fk create spawn <X> <Y> <Z>");
						}
					} else {
						sender.sendMessage(ChatColor.RED+"Usage: /fk create <team | spawn>");
						return true;
					}
				}
				
			} else {
				//Default message
				sender.sendMessage(ChatColor.BLUE + "=========== Fallen Kingdom ===========");
				sender.sendMessage(ChatColor.GREEN + "/fk start : start  game\n");
				if (sender.isOp()){
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "/fk create <team | spawn> : Create a game\n/fk remove : Remove the game settings\n");
				}
				sender.sendMessage(ChatColor.BLUE + "===================================");
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
				commandList.add("create");
				commandList.add("remove");
				ArrayList<String> commandFound = new ArrayList<String>();
				for(String s: commandList) {
					if(s.toLowerCase().startsWith(args[0].toLowerCase())){
						commandFound.add(s);
					}
				}
				return commandFound;
				
			} else if(args.length == 2){
				
				if(args[0].equalsIgnoreCase("create")){
					ArrayList<String> commandFound = new ArrayList<String>();
					commandList.add("spawn");
					commandList.add("team");
					for(String s: commandList) {
						if(s.toLowerCase().startsWith(args[1].toLowerCase())){
							commandFound.add(s);
						}
					}
					return commandFound;
				}
			}
		}
		return null;
	}

}
