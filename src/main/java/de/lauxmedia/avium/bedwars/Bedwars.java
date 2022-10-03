package de.lauxmedia.avium.bedwars;

import de.lauxmedia.avium.bedwars.commands.BedWarsCommand;
import de.lauxmedia.avium.bedwars.gamestate.GameState;
import de.lauxmedia.avium.bedwars.listener.JoinListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Bedwars extends JavaPlugin {

    private static Bedwars instance;

    private GameState gameState;
    private PluginManager pluginManager;

    @Override
    public void onEnable() {
        // set instance
        instance = this;
        // set gameState
        gameState = GameState.LOBBY;
        // set pluginManager
        pluginManager = getServer().getPluginManager();
        // registerListener
        registerListener();
        // register Commands
        registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerListener() {
        pluginManager.registerEvents(new JoinListener(), getInstance());
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("bedwars")).setExecutor(new BedWarsCommand());
    }

    public Bedwars getInstance() {
        return instance;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }
}
