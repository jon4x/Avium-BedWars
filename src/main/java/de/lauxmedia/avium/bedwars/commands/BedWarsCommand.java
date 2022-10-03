/*
 *
 * Avium BedWars
 * Copyright (c) 2022 @ Jonas Laux
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *
 */

package de.lauxmedia.avium.bedwars.commands;

import org.bukkit.Location;
import de.lauxmedia.avium.bedwars.util.Locations;
import de.lauxmedia.avium.bedwars.util.PrefixStrings;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.jetbrains.annotations.NotNull;
import org.bukkit.command.CommandSender;
import de.lauxmedia.avium.bedwars.Bedwars;
import org.bukkit.plugin.Plugin;
import org.bukkit.command.CommandExecutor;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BedWarsCommand implements CommandExecutor, TabCompleter
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
                // setup help
                if (args[0].equalsIgnoreCase("setup")) {
                    player.sendMessage(PrefixStrings.getInfoPrefix() + "§7BedWars setup instructions: \n§eStep 1 §8- §7/bedwars setlobby \n§eStep 2 §8- §7/bedwars setconfig <teams> <player per team> \n§eStep 3 §8- §7/bedwars createmap <name> \n§eStep 4 §8- §7/bedwars setspawn <map> <color> \n§eStep 5 §8- §7/bedwars enablemap <name> \n§eStep 6 §8- §7/bedwars finish \n");
                }
                // set lobby
                if (args[0].equalsIgnoreCase("setlobby")) {
                    final Location location = player.getLocation();
                    this.plugin.getConfig().set("locations.spawn.x", location.getX());
                    this.plugin.getConfig().set("locations.spawn.y", location.getY());
                    this.plugin.getConfig().set("locations.spawn.z", location.getZ());
                    this.plugin.getConfig().set("locations.spawn.yaw", location.getYaw());
                    this.plugin.getConfig().set("locations.spawn.pitch", location.getPitch());
                    this.plugin.getConfig().set("locations.spawn.world", location.getWorld().getName());
                    this.plugin.saveConfig();
                    player.sendMessage(PrefixStrings.getInfoPrefix() + "§7Lobby has been set §asuccessfully§7.");
                    Locations.setLobbySpawn(location);
                }
                // set config
                if (args[0].equalsIgnoreCase("setconfig")) {
                    if (Locations.getLobbySpawn() == null) {
                        player.sendMessage(PrefixStrings.getWarningPrefix() + "§7Please use §c/bedwars setup §7for setup instructions.");
                    }
                    else if (args.length != 3) {
                        player.sendMessage(PrefixStrings.getWarningPrefix() + "§7Please use: §c/bedwars setconfig <teams> <player per team>");
                    }
                    else {
                        final int teams = Integer.parseInt(args[1]);
                        final int players = Integer.parseInt(args[2]);
                        this.plugin.getConfig().set("config.teams", teams);
                        this.plugin.getConfig().set("config.player", players);
                        this.plugin.saveConfig();
                        player.sendMessage(PrefixStrings.getInfoPrefix() + "§7Config changed to §e" + teams + "x" + players + "§7.");
                    }
                }
                // create map
                if (args[0].equalsIgnoreCase("createmap")) {
                    if (Locations.getLobbySpawn() == null) {
                        player.sendMessage(PrefixStrings.getWarningPrefix() + "§7Please use §c/bedwars setup §7for setup instructions.");
                    }
                    else if (args.length != 2) {
                        player.sendMessage(PrefixStrings.getWarningPrefix() + "§7Please use: §c/bedwars createmap <name>");
                    }
                    else {
                        final String mapName = args[1];
                        this.plugin.getConfig().set("locations.maps." + mapName.toLowerCase() + ".enabled", false);
                        this.plugin.saveConfig();
                        player.sendMessage(PrefixStrings.getInfoPrefix() + "§7Map §e" + mapName.toLowerCase() + " §7has been created §asuccessfully§7.");
                    }
                }
                // set spawns
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


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return null;
    }
}
