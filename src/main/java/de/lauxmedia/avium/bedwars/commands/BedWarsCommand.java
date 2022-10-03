package de.lauxmedia.avium.bedwars.commands;

import de.lauxmedia.avium.bedwars.util.PrefixStrings;
import org.bukkit.Utility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BedWarsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // check if console
        if (!(sender instanceof Player)) { return true; }
        // cast Player
        Player player = (Player) sender;
        // check command
        if (command.getName().equals("bedwars")) {
            if (args.length == 0) {
                return false;
            }
            // check permission
            if (player.hasPermission("bedwars.setup")) {
                // setup help
                if (args[0].equalsIgnoreCase("setup")) {
                    player.sendMessage(PrefixStrings.getInfoPrefix() + "§7BedWars Setup Tutorial: \n" +
                            "§eStep 1 §8- §7/bedwars setlobby \n" +
                            "§eStep 2 §8- §7/bedwars createmap <name> \n" +
                            "§eStep 3 §8- §7/bedwars setconfig <amount of teams> <amount of players>\n" +
                            "§eStep 4 §8- §7/bedwars setspawn <map> <color>");
                }
                // setlobby
                if (args[0].equalsIgnoreCase("setlobby")) {
                    player.sendMessage(PrefixStrings.getInfoPrefix() + "§7Lobby has been set §asuccessfully§7.");
                }
                // setspawn
                if (args[0].equalsIgnoreCase("setspawn")) {
                    if (args.length != 3) {
                        player.sendMessage(PrefixStrings.getWarningPrefix() + "§7Please use: §c/bedwars setspawn <map> <color>");
                    } else {
                        player.sendMessage(PrefixStrings.getInfoPrefix() + "§7Spawn for team §a" + args[2] + "§7 has been set §asuccessfully§7.");
                    }
                }
            }
        }
        return true;
    }

}
