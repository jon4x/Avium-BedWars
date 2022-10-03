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

package de.lauxmedia.avium.bedwars.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import de.lauxmedia.avium.bedwars.util.Locations;
import de.lauxmedia.avium.bedwars.gamestate.GameState;
import de.lauxmedia.avium.bedwars.Bedwars;
import de.lauxmedia.avium.bedwars.util.PrefixStrings;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.Listener;

public class JoinListener implements Listener
{
    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        Player player = event.getPlayer();
        int onlinePlayers = Bedwars.getInstance().getServer().getOnlinePlayers().size();
        int maxPlayers = Bedwars.getInstance().getServer().getMaxPlayers();
        event.setJoinMessage(PrefixStrings.getPositivePrefix() + "§a" + player.getName() + " joined the game. §8(§7" + onlinePlayers + "§8/§7" + maxPlayers + "§8)");
        if (player.hasPermission("bedwars.setup")) {
            player.sendMessage(PrefixStrings.getWarningPrefix() + "§7Please use: §c/bedwars setup");
        }
        if (Bedwars.getInstance().getGameState() == GameState.LOBBY && Locations.getLobbySpawn() != null) {
            player.teleport(Locations.getLobbySpawn());
        }
        player.setHealth(20.0);
        player.setHealthScale(2.0);
        player.setFoodLevel(20);
    }
}
