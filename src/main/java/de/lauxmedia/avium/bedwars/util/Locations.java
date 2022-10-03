package de.lauxmedia.avium.bedwars.util;

import org.bukkit.World;
import de.lauxmedia.avium.bedwars.Bedwars;
import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;

public class Locations
{
    private static final Configuration configuration;
    private static Location lobbySpawn;
    private static final HashMap<String, Location> teamSpawns;

    public static void getLocationsFromConfig() {
        Locations.lobbySpawn = getLobbySpawnFromConfig();
    }

    private static Location getLobbySpawnFromConfig() {
        if (Locations.configuration.get("locations.spawn") == null) {
            return null;
        }
        final String worldString = Locations.configuration.getString("locations.spawn.world");
        final World world = Bedwars.getInstance().getServer().getWorld(worldString);
        final double x = Locations.configuration.getDouble("locations.spawn.x");
        final double y = Locations.configuration.getDouble("locations.spawn.y");
        final double z = Locations.configuration.getDouble("locations.spawn.z");
        final float yaw = (float)Locations.configuration.getDouble("locations.spawn.yaw");
        final float pitch = (float)Locations.configuration.getDouble("locations.spawn.pitch");
        return new Location(world, x, y, z, yaw, pitch);
    }

    public static HashMap<String, Location> getTeamSpawns() {
        return Locations.teamSpawns;
    }

    public static Location getLobbySpawn() {
        return Locations.lobbySpawn;
    }

    public static void setLobbySpawn(Location lobbySpawn) {
        Locations.lobbySpawn = lobbySpawn;
    }

    static {
        configuration = (Configuration)Bedwars.getInstance().getConfig();
        teamSpawns = new HashMap<String, Location>();
    }
}