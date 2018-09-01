package com.fk.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.fk.main.Main;

public class FkCommand implements CommandExecutor, TabCompleter{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.isOp()) {
			if(args.length > 0) {
				if(args[0].equalsIgnoreCase("start")){
					sender.sendMessage(ChatColor.RED+ args[0]+" command unavailable!");
					return true;
				}
				
			} else {
				
			}
			
			//Default message
			sender.sendMessage(ChatColor.BLUE + "=========== Fallen Kingdom ===========");
			sender.sendMessage(ChatColor.GREEN + "/fk start : start  game\n");
			if (sender.isOp()){
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "/fk create [game] [minPlayer] [maxPlayer] : Create a game (admin)\n/fk remove [game] : Remove a game (admin)\n");
			}
			sender.sendMessage(ChatColor.BLUE + "===================================");
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("fk") || cmd.getName().equalsIgnoreCase("fallenkingdom")) {
			if(args.length == 1){
				ArrayList<String> commandFound = new ArrayList<String>();
				for(String s: Main.commandList) {
					if(s.toLowerCase().startsWith(args[0].toLowerCase())){
						commandFound.add(s);
					}
				}
				return commandFound;
			}
		}
		return null;
	}

}
