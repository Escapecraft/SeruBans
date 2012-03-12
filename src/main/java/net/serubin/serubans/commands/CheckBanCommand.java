package net.serubin.serubans.commands;

import net.serubin.serubans.SeruBans;
import net.serubin.serubans.util.HashMaps;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckBanCommand implements CommandExecutor {

    private SeruBans plugin;

    public CheckBanCommand(SeruBans plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd,
            String commandLabel, String[] args) {
        Player player = (Player) sender;
        
        if (commandLabel.equalsIgnoreCase("checkban")) {
            boolean isBanned = HashMaps.getBannedPlayers().containsKey(args[0]);

            if (isBanned) {
                int id = HashMaps.getBannedPlayers().get(args[0]);
                sender.sendMessage(ChatColor.RED + args[0] + " is banned.");
                sender.sendMessage(ChatColor.RED + "Ban id: " + ChatColor.YELLOW + id);
            } else {
                sender.sendMessage(ChatColor.GREEN + args[0] + " is not banned.");
            }
        }
        return false;
    }

}
