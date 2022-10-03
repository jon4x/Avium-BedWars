package de.lauxmedia.avium.bedwars.commands;

import org.bukkit.Location;
import de.lauxmedia.avium.bedwars.util.Locations;
import de.lauxmedia.avium.bedwars.util.PrefixStrings;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.jetbrains.annotations.NotNull;
import org.bukkit.command.CommandSender;
import de.lauxmedia.avium.bedwars.Bedwars;
import org.bukkit.plugin.Plugin;
import org.bukkit.command.CommandExecutor;

public class BedWarsCommand implements CommandExecutor
{
    Plugin plugin;

    public BedWarsCommand() {
        this.plugin = Bedwars.getInstance();
    }

    public boolean onCommand(@NotNull final CommandSender sender, @NotNull final Command command, @NotNull final String label, @NotNull final String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        final Player player = (Player)sender;
        if (command.getName().equals("bedwars")) {
            if (args.length == 0) {
                return false;
            }
            if (player.hasPermission("bedwars.setup")) {
                if (args[0].equalsIgnoreCase("setup")) {
                    player.sendMessage(PrefixStrings.getInfoPrefix() + "§7BedWars setup instructions: \n§eStep 1 §8- §7/bedwars setlobby \n§eStep 2 §8- §7/bedwars setconfig <teams> <players per team> \n§eStep 3 §8- §7/bedwars createmap <name> \n§eStep 4 §8- §7/bedwars setspawn <map> <color> \n§eStep 5 §8- §7/bedwars enablemap <name> \n§eStep 6 §8- §7/bedwars finish \n");
                }
                if (args[0].equalsIgnoreCase("setlobby")) {
                    final Location location = player.getLocation();
                    this.plugin.getConfig().set("locations.spawn.x", (Object)location.getX());
                    this.plugin.getConfig().set("locations.spawn.y", (Object)location.getY());
                    this.plugin.getConfig().set("locations.spawn.z", (Object)location.getZ());
                    this.plugin.getConfig().set("locations.spawn.yaw", (Object)location.getYaw());
                    this.plugin.getConfig().set("locations.spawn.pitch", (Object)location.getPitch());
                    this.plugin.getConfig().set("locations.spawn.world", (Object)location.getWorld().getName());
                    this.plugin.saveConfig();
                    player.sendMessage(PrefixStrings.getInfoPrefix() + "§7Lobby has been set §asuccessfully§7.");
                    Locations.setLobbySpawn(location);
                }
                if (args[0].equalsIgnoreCase("setconfig")) {
                    if (Locations.getLobbySpawn() == null) {
                        player.sendMessage(PrefixStrings.getWarningPrefix() + "§7Please use §c/bedwars setup §7for setup instructions.");
                    }
                    else if (args.length != 3) {
                        player.sendMessage(PrefixStrings.getWarningPrefix() + "§7Please use: §c/bedwars setconfig <teams> <players per team>");
                    }
                    else {
                        final int teams = Integer.parseInt(args[1]);
                        final int players = Integer.parseInt(args[2]);
                        this.plugin.getConfig().set("config.teams", (Object)teams);
                        this.plugin.getConfig().set("config.players", (Object)players);
                        this.plugin.saveConfig();
                        player.sendMessage(PrefixStrings.getInfoPrefix() + "§7Config changed to §e" + teams + "x" + players + ".");
                    }
                }
                if (args[0].equalsIgnoreCase("createmap")) {
                    if (Locations.getLobbySpawn() == null) {
                        player.sendMessage(PrefixStrings.getWarningPrefix() + "§7Please use §c/bedwars setup §7for setup instructions.");
                    }
                    else if (args.length != 2) {
                        player.sendMessage(PrefixStrings.getWarningPrefix() + "§7Please use: §c/bedwars createmap <name>");
                    }
                    else {
                        final String mapName = args[1];
                        this.plugin.getConfig().set("locations.maps." + mapName.toLowerCase() + ".enabled", (Object)false);
                        this.plugin.saveConfig();
                        player.sendMessage(PrefixStrings.getInfoPrefix() + "§7Map §e" + mapName.toLowerCase() + " §7has been created §asuccessfully§7.");
                    }
                }
                if (args[0].equalsIgnoreCase("setspawn")) {
                    if (Locations.getLobbySpawn() == null) {
                        player.sendMessage(PrefixStrings.getWarningPrefix() + "§7Please use §c/bedwars setup §7for setup instructions.");
                    }
                    else if (args.length != 3) {
                        player.sendMessage(PrefixStrings.getWarningPrefix() + "§7Please use: §c/bedwars setspawn <map> <color>");
                    }
                    else {
                        player.sendMessage(PrefixStrings.getInfoPrefix() + "§7Spawn for team §a" + args[2] + "§7 has been set §asuccessfully§7.");
                    }
                }
            }
        }
        return true;
    }
}
