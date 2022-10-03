//
// Decompiled by Procyon v0.5.36
//

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
