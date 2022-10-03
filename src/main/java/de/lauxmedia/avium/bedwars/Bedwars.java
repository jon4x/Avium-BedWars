package de.lauxmedia.avium.bedwars;

import de.lauxmedia.avium.bedwars.commands.BedWarsCommand;
import java.util.Objects;
import de.lauxmedia.avium.bedwars.listener.QuitListener;
import de.lauxmedia.avium.bedwars.listener.JoinListener;
import de.lauxmedia.avium.bedwars.util.Locations;
import org.bukkit.plugin.PluginManager;
import de.lauxmedia.avium.bedwars.gamestate.GameState;
import org.bukkit.plugin.java.JavaPlugin;

public final class Bedwars extends JavaPlugin
{
    private static Bedwars instance;
    private static GameState gameState;
    private static PluginManager pluginManager;

    public void onEnable() {
        instance = this;
        gameState = GameState.LOBBY;
        pluginManager = getServer().getPluginManager();
        registerListener();
        registerCommands();
        loadConfigs();
        Locations.getLocationsFromConfig();
        setSlots();
    }

    public void onDisable() {
    }

    public void setSlots() {
        int teams = (int) getConfig().get("config.teams");
        int player = (int) getConfig().get("config.player");
        getServer().setMaxPlayers(teams * player);
    }

    private void loadConfigs() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }

    private void registerListener() {
        Bedwars.pluginManager.registerEvents(new JoinListener(), getInstance());
        Bedwars.pluginManager.registerEvents(new QuitListener(), getInstance());
    }

    private void registerCommands() {
        Objects.requireNonNull(this.getCommand("bedwars")).setExecutor(new BedWarsCommand());
    }

    public static Bedwars getInstance() {
        return Bedwars.instance;
    }

    public GameState getGameState() {
        return Bedwars.gameState;
    }

    public static void setGameState(final GameState gameState) {
        Bedwars.gameState = gameState;
    }

    public static PluginManager getPluginManager() {
        return Bedwars.pluginManager;
    }
}