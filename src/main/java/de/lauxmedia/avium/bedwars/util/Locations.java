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
        configuration = Bedwars.getInstance().getConfig();
        teamSpawns = new HashMap<>();
    }
}