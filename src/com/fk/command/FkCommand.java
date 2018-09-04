package com.fk.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
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
		
		Player p = (Player) sender;
		
		if(Main.gameSetup && !Main.gameStatus && p.isOp()) {
			sender.sendMessage(ChatColor.LIGHT_PURPLE+"Your server is setup and ready to play !\nDelete "+ChatColor.WHITE+"config.yml"+ChatColor.LIGHT_PURPLE+" and reload the server to start a new setup !");
		} else if(sender.isOp() && !Main.gameStatus) {
			
			
			Location l = p.getLocation();
			int x = l.getBlockX(), y= l.getBlockY(), z = l.getBlockZ();
			
			if(args.length > 0) {
				
				if(args[0].equalsIgnoreCase("start") && Main.spawnStatus && Main.lobbyStatus) {
					if(Main.gameSetup){
						sender.sendMessage(ChatColor.RED+"This game has already been launched !\nYou can now start your own FallenKingdom !");
						return true;
					}
					Main.gameSetup = true;
					sender.sendMessage(ChatColor.GREEN+"Your server is now setup !\nYou can start to play when you want !\nGood luck.\n");
					sender.sendMessage(ChatColor.LIGHT_PURPLE+"\nYou have to disconnect and reconnect to start playing.");
					for(Player p1 : Bukkit.getOnlinePlayers()) {
						if(!p1.equals(p)) {
							p1.kickPlayer("An admin has just start the FallenKingdom.");
						}
					}
				}
				
				else if(args[0].equalsIgnoreCase("spawn") || args[0].equalsIgnoreCase("lobby")){
					if(args.length == 1){
						if(Main.config.getInt(args[0]+".y") == -1 || !(Main.config.getInt(args[0]+".x") == x && Main.config.getInt(args[0]+".y") == y && Main.config.getInt(args[0]+".z") == z)) {
							if(CreateGameConfig.createSpawnLobby(l.getWorld().getName(), args[0], x, y, z)){
								sender.sendMessage(ChatColor.GREEN+"The "+args[0]+" has been set to "+ChatColor.LIGHT_PURPLE+Main.config.getInt(args[0]+".x")+" "+Main.config.getInt(args[0]+".y")+" "+Main.config.getInt(args[0]+".z"));
							} 
						} else {
							sender.sendMessage(ChatColor.RED+"The "+args[0]+" is already set to "+ChatColor.LIGHT_PURPLE+Main.config.getInt(args[0]+".x")+" "+Main.config.getInt(args[0]+".y")+" "+Main.config.getInt(args[0]+".z"));
							sender.sendMessage(ChatColor.RED+"You can still modify it by using: /fk "+args[0]+" <X> <Y> <Z>");
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
						    	sender.sendMessage(ChatColor.RED+"Usage: /fk "+args[0]+" <X> <Y> <Z>");
						        return true;
						    }
						}
						if(Main.config.getInt(args[0]+".x")==x && Main.config.getInt(args[0]+".y")==y &&Main.config.getInt(args[0]+".z")==z){
							sender.sendMessage(ChatColor.RED+"The "+args[0]+" is already set to "+ChatColor.LIGHT_PURPLE+Main.config.getInt(args[0]+".x")+" "+Main.config.getInt(args[0]+".y")+" "+Main.config.getInt(args[0]+".z"));
							return true;
						}
						CreateGameConfig.createSpawnLobby(l.getWorld().getName(), args[0], x, y, z);
						sender.sendMessage(ChatColor.GREEN+"The "+args[0]+" has been set to "+ChatColor.LIGHT_PURPLE+Main.config.getInt(args[0]+".x")+" "+Main.config.getInt(args[0]+".y")+" "+Main.config.getInt(args[0]+".z"));
						
					} else {
						sender.sendMessage(ChatColor.RED+"Usage: /fk "+args[0]+" <X> <Y> <Z>");
						return true;
					}
				}
			} else {
				//Default message
				sender.sendMessage(ChatColor.BLUE + "=========== Fallen Kingdom ============");
				String spawnStatusColor= ChatColor.RED+""+Main.spawnStatus;
				String lobbyStatusColor = ChatColor.RED+""+Main.lobbyStatus;
				if(Main.config.getInt("spawn.y")!=-1) {
					Main.spawnStatus = true;
					spawnStatusColor = ChatColor.GREEN+""+Main.spawnStatus;
				}
				if(Main.config.getInt("lobby.y")!=-1){
					Main.lobbyStatus = true;
					lobbyStatusColor = ChatColor.GREEN+""+Main.lobbyStatus;
				}
				if(Main.spawnStatus && Main.lobbyStatus) {
					sender.sendMessage(ChatColor.GREEN + "/fk start : start game\n");
				}
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "/fk spawn : Setup the spawn ("+spawnStatusColor+ChatColor.LIGHT_PURPLE+")\n/fk lobby : Change lobby spawns ("+lobbyStatusColor+ChatColor.LIGHT_PURPLE+")\n");
				sender.sendMessage(ChatColor.BLUE + "====================================");
			}
		} else if(Main.gameStatus && Main.day>=1) {
			if(args.length==1) {
				if(args[0].equalsIgnoreCase("setbase")) {
					if(Main.teamLeader.get(Main.getPlayerTeam(p)).getName().equals(sender.getName())) {
						Main.teamBase.put(Main.getPlayerTeam(p), p.getLocation());
						sender.sendMessage("You succesfully set your base in "+p.getLocation().getBlockX()+" "+p.getLocation().getBlockY()+" "+p.getLocation().getBlockZ());
					} else {
						sender.sendMessage(Main.teamLeader.get(Main.getPlayerTeam(p)).getName()+ChatColor.RED+" is the team leader, he has to setup your base !");
					}
				} else if(args[0].equalsIgnoreCase("pause")) {
					if(Main.vote.get(p)) {
						p.sendMessage(ChatColor.RED+"You already vote for a pause.");
						return true;
					}
					Main.votePause++;
					Main.vote.put(p, true);
					if(Main.votePause==1) {
						Bukkit.broadcastMessage(p.getName()+ChatColor.LIGHT_PURPLE+" asked for a pause. Use "+ChatColor.WHITE+"/fk pause"+ChatColor.LIGHT_PURPLE+" if you agree.");
					}
				}
			}
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("fk") || cmd.getName().equalsIgnoreCase("fallenkingdom")) {
			ArrayList<String> commandList = new ArrayList<String>();
			if(args.length == 1){
				if(Main.spawnStatus && Main.lobbyStatus) {
					commandList.add("start");
				}
				commandList.add("spawn");
				commandList.add("lobby");
				ArrayList<String> commandFound = new ArrayList<String>();
				for(String s: commandList) {
					if(s.toLowerCase().startsWith(args[0].toLowerCase())){
						commandFound.add(s);
					}
				}
				return commandFound;
			}
		}
		return new ArrayList<String>();
	}

}
