package net.serubin.serubans.commands;

import net.serubin.serubans.SeruBans;
import net.serubin.serubans.util.ArgProcessing;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarnCommand implements CommandExecutor {

	ArgProcessing ap;
	OfflinePlayer offPlayer;
    Server server = Bukkit.getServer();
	Player victim;
	String mod;
	String reason = "";
	private String WarnMessage;
	private String name;
	private SeruBans plugin;
	private String WarnPlayerMessage;

	public WarnCommand(String WarnMessage, String WarnPlayerMessage, String name, SeruBans plugin) {
		// TODO Auto-generated constructor stub
		this.WarnMessage = WarnMessage;
		this.WarnPlayerMessage = WarnPlayerMessage;
		this.name = name;
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {
		// TODO Auto-generated method stub
		
		if (commandLabel.equalsIgnoreCase("Warn")) {
			Player player = (Player) sender;
			int hide = 0;
			if(args.length == 0){
				return false;
			}
			else if(args.length == 1){
				reason = "undefined";
			}
			else if(args.length > 1){
				reason = ArgProcessing.reasonArgs(args);
			}
			if(args[1] == "-h"){
				hide = 1;
			}
			mod = player.getName();
			victim = server.getPlayer(args[0]);
			//processes Warn message
			
			
			String line = "";
			if(victim != null){
				//Warns and broadcasts message
				ArgProcessing.GlobalMessage(WarnMessage, reason, mod, victim);
				SeruBans.printServer(line);
				SeruBans.printInfo(mod + " warned " + victim.getName() + " for " + reason);
				SeruBans.printInfo(ArgProcessing.PlayerMessage(WarnPlayerMessage, reason, mod));
				SeruBans.printInfo(WarnMessage);
				victim.sendMessage(ArgProcessing.GetColor(WarnPlayerMessage));
				victim.sendMessage(reason);
				
				//adds player to db
				return true;
			}else{
				try{
					victim = offPlayer.getPlayer();
					} catch(NullPointerException NPE) {
						victim = null;
					}
				if(victim !=null){
					//broadcasts message
					ArgProcessing.GlobalMessage(WarnMessage, reason, mod, victim);
					SeruBans.printServer(line);
					SeruBans.printInfo(mod + " warned " + victim.getName() + " for " + reason);;
					return true;
				}else{
					player.sendMessage("This Player was not found!");
					return true;
				}
			}
			

		}
		
		return false;
	}

}
