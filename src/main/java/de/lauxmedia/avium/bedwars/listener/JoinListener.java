package de.lauxmedia.avium.bedwars.listener;

import de.lauxmedia.avium.bedwars.util.PrefixStrings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        // get Player
        Player player = event.getPlayer();
        // join Message
        event.setJoinMessage(null);
        // check if setup complete
        if (player.hasPermission("bedwars.setup")) {
            player.sendMessage(PrefixStrings.getWarningPrefix() + "§7Please use: §c/bedwars setup");
        }
    }

}
