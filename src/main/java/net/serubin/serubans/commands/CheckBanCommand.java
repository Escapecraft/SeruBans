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

        if (commandLabel.equalsIgnoreCase("checkban")) {
            if (SeruBans.hasPermission(sender, SeruBans.CHECKBANPERM)) {
                if (args.length != 1) {
                    return false;
                }
                String victim = args[0].toLowerCase();
                boolean isBanned = HashMaps.keyIsInBannedPlayers(victim);

                if (isBanned) {
                    int id = HashMaps.getBannedPlayers(victim);
                    sender.sendMessage(ChatColor.RED + args[0] + " is banned.");
                    sender.sendMessage(ChatColor.RED + "Ban id: "
                            + ChatColor.YELLOW + id);
                    return true;
                } else {
                    sender.sendMessage(ChatColor.GREEN + args[0]
                            + " is not banned.");
                    return true;
                }
            }
            return true;
        }
        return false;
    }

}
